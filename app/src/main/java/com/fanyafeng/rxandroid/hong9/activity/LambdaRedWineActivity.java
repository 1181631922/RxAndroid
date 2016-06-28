package com.fanyafeng.rxandroid.hong9.activity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.rxandroid.BaseActivity;
import com.fanyafeng.rxandroid.R;
import com.fanyafeng.rxandroid.adapter.RVAdapter;
import com.fanyafeng.rxandroid.hong9.bean.BannerBean;
import com.fanyafeng.rxandroid.hong9.bean.ProductBean;
import com.fanyafeng.rxandroid.hong9.fragment.ViewPagerFragment;
import com.fanyafeng.rxandroid.hong9.network.Urls;
import com.fanyafeng.rxandroid.hong9.response.GetMainResponse;
import com.fanyafeng.rxandroid.hong9.service.ApiService;
import com.fanyafeng.rxandroid.retrofit.response.TaoBaoGetIpInfoResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LambdaRedWineActivity extends BaseActivity {

    private final static String imageUri = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCADcASUDASIAAhEBAxEB/8QAGwAAAgMBAQEAAAAAAAAAAAAAAQIAAwUEBgf/xAA9EAACAgECBQEFBgUCBAcAAAAAAQIDBAUREiExUWFBBhMiMrEUI1JxwdEzQoGR4aHwFWKi0kNTcpKywuL/xAAaAQEAAgMBAAAAAAAAAAAAAAAAAQIDBAUG/8QANREAAgIBAgIGCQMEAwAAAAAAAAECAwQRMRIhBRNBUbHwFCIjMmFxgaHhQsHRM1JikXKi8f/aAAwDAQACEQMRAD8AwkMKhjYNQIQBAO/Ts1Y8vdWfw5Pr2ZufQ8oaWnZ/u9qbn8D5Rk/TwdbBzNNKp/Q1b6f1RNkhCHYNQngjIAAgOofyByJAGR9CMnoCQAfYPIVkgjFCwEgV9irIvqxqZXXTjCuK3lJ9EDLyqMLHlfkTUIR6v1b7Luzw+qapdqlylNOFMH93Vv08vu/oaeXlxoj3vuM1VTm/gHV9Vnql0fh4KK23XB9fzfnwZwwDzdlkrJOcnzZ0IxUVohQDCmMsAAQACgYwoJFYrGZv6F7PSzXHJyouOP1jF9Z/4MVtsao8UjYxcWzJs6utfg59F0GefJX3Jwxk/wCs/wAvB6bLyo48fc0/Oltv1Uf8lmZlxpj9nx9k48m10j4XkymtjWqplkS623bsR1MnKrwIPGxnrL9UvPlfMpa2RXJF0kVSOieeKZI48nIVKUYrjtl8sN/9X2RZl5XuWqq48d8uke3l+BcbCcd7LHxWS5yk/X/BRvsR0cPCdvrz5R8TgWme+bsu+KyT3bXIhtcCRCmiO0lFLRI1UECCZjygQgCAQhCEg1dOz+lFz8Qk/ozWPKGxp2fx7UXP4ukZP18HYwczX2dm/Yal1OnrRNMDJyIzqmqT0BzCDckAfUj6EYPQEkF9Q/1A+pIAzlzs6jT8Z35E9o9IpdZPsl3F1LUqNMxve3PeT5QrXzTfj9/Q8Lm5t+oZLvyJby6Rivlguy/f1NLMzY0LhXORmppc3q9h9R1G/Usj3t3wxj/DqT5QX6vycfoEB5yc5Tk5SerOikktEQBCFCQACAAUAwoABWN6nrNA9m/lzM+HmuqX1f7GK66NMeKRuYeHZlWcEPq+459A9m3fw5ebBqrrCt9ZeX4N3Nzdt6cd7JcnNenhfuHNzveb1UPavo5L18LwZzRr048rZdbd9Eb+XnVYtbxcP6y7/l5+RW1stiuRayuR0Tz5VIz8zLdc/cUJTyH6ekF3f7D5eXL3jxsXaV380uqr/wA+C3D0+NEN3vKb5yk+bbKSl2I62D0e7faW+74/gow8BVJzm3OyT3lKXVs63FJF7WxVJFDtvuRU0QLXMgMZoIIqCZTyYxABACQBAAkIAA29Pz/fJU2v7xdH+L/JoHlU2mmns10aNvAzvtMVXY9rV/1HcwczrPZz38TTuq4fWjsdwA7g/qdM1yAZAbkgm5narq1OlUbz+O6f8OpPnL9l5E1jWatLq4VtZkzXwVfrLsvqeIvvtyb5332Oy2fWT+i7Lwc7NzlSuCHveBsU0ufN7DZWVdm5MsjInx2S5dlFdkvRFJGA8/KTk9Xub6WnJEAEBBIABAQCAIAAgEm3sk23y5DRjKc1GEXKUnskvVntdC9noYEFmZvC79t1F9K/8mC++NMdXv3G7hYNmVPSPKK3fcU6B7ORx1HNz4r3nzQrl0h5fk0MzNd+9db2q9X+L/BMzLeS+GO6q7esvz/Y5GY6MaUpdddv2LuNrN6Qrrr9Fw+Ue19r8/8AnIRiPqOxH1OgcIrZmZWXZda8XDfxrlZaukPC8/QORlWZlrxcOTUU9rLl6eI+fJ34eDXi1KMIpJGJy12O50f0bxaW3Ll2Lv8AwUYWBDGrSS59W31bOprYua2K5FTtyKZFUi6RTIGNlT6kC1zICh2oIqCZTyIxAB3ACQBAAkAQAJIycZKUW00900AgT05oG/g5scqHDLlauq7+UdR5eE5VzU4PaS5pm9h5kcqvtOPzR/VHfwsxWrgn73iaV1XDzWx0sx9a1yvTYe5p2nlyXKL6QXeX6L1Ktc19YPFjYrUspr4n1VX5934/uePlKUpynKTlOT3lKT3bfdlM3PVfs69/AtTRxetLYNlk7bZ22zlOyb3lOXVsRhYDhNt82bxAEIyCSA9CAZAIAIAADV1zusjXXFznJ7KKXNsaii3JujTTBzsk9lFHu9I0WjRcf397Ushr4pfh8I178hVLRc29kb+DgSyXxN6QW78+UVaJoNWk0/a8txeRtvu+la7LyXZWVLJltzjWnyj38smRkTyJ7y5RXyw7eX5KGRj4rUutu5y8DJn9IxcPRsXlWvv5+4rEYzEk1GLcmklzbfobxxRWZF19mp2PHxZOOOntZav5/EfHkNllmrz91TvHDT2lLo7f/wA/U2MbFhRWoxikkYpS12PRdHdF8Ol16+S/n+CnFwq8aqMYRSS7HQ1sOxGQdqT1KpFciyRXIGNlUiqRbIqkQY2VPqQL6kBQ0szCydPyXj5dUqrUk+F9mUH2D2h9n8PX9OU6Zx4o7+6tXPgfZ+H6nyXLxL8DKsxsmtwtrezT+q7o1cHOhlQ12kt0eRalF8Mt/H5FQRQm+AkAQAJCbkAIQBAAkU5w3ddkq5bNKUeqAAlNp6oaamBdRPHtcLOb68X4vJWb2RRDIq4J/mpLqmYdtU6bXXNbSX9mu6KsuhCMBCCSACwAEAQhAAXYuLdmZEaKIOdkuiXp5Y2FhX6hkxox4cU31fol3Z73T9OxdBxOXx3S+aXrN9l4Na/I6v1Y85PsOjg4Dv1ssfDWt3/Aml6VjaFi+8m1O+S2lPbm32Ql908izjnyS+WPpH/JLbZ3WOdj5+iXSKKmWxsXgfWWc5P7Fc/pFWrqKFw1r7/Pz8WBisZlN11dFUrbZqEIrdyfobhyUteSJZOFcJTnJRhFbuT5JIyH73WbNkpQwk+SfJ2+X48DRru1i6M7YuvEi94VPrLzL9jcpojVBJLbYxN6/I9R0d0Wqkrb163Yu75/Erox40wSikti1jsrZB15PURiSHZWwY2VyK5FkiqQKMrkVSLZFUiDGyt9SEfUgKH2vDy54d3FFOVcv4kO/leSn2m9m8fXcGORjyirUt6bf/q/H0ETOvCzZYdjTTlRP54fqv8AfM5/SGFNT9KxvfW6/uX8nkotSXBL6Pu/B8hvotxcidF9brtrfDKL6plZ9U9q/ZerV8ZZeI4/aFH7ufpNfhb+jPlllc6bZVWwlCyD4ZRktmn2NnCzYZVfFHftXcRzT4ZboBNwENwkbcBNybgEIDcgAQEAAEoycaOTXwvlJc4y7FwADz9kJ12OE1tJdUKbWXixyYdpr5Zfo/BizjKE3CSakuTXYqyyYGBhAQSQ6tP07I1PJVFEef8ANJ9IruyzS9KyNVyVVStor57H0iv3Pd0UYuiYkcfHhvN8+fWT7s1b8hxfV185M6eFgqyPX3vhrX3+Xn4IXEw8XQsNV1Litl1f803+xzWWStsc5veT/svCJOUpzc5vim+rEZlxsVVetLnJ9pr9IdIvJ0rrXDWtl/JG+orZGc+Xl1YdLttk9uiS5uT7LybTehzoxcmktw5ORVi0yuumowj69/CXqzNpx79VvjfkxcKIveql+nl939BsbEv1HIjlZi2Uf4dPpBfq/Ju11quKSRib1PWdHdGLGStt5z8PyLVTGuKSQzGYjZB029RWIxmxGwUYjK5DsrkCjEkVyHZXIFGVyK5FkiqRBRlb6kI+pAUPsCY6ZSmOmbB4478HO+yyddvxY0/mX4fK8dzJ9r/ZSOoV/bcNJ5Kjumulsez89mdKZ3afnrHX2e/njS9X/I/2+hxM7Dsps9MxVz/Uu/4/Px+e+RNTSjJ6NbPz2HxqUZQk4yTjJPZprZpg3Po/tl7JPI4s/ChvkJbyjH/xV/3fU+bvk9n1N7Ey68qtWQITerjLk0HcgNybm0SHcgNyABBuQG4AQE3AAE5czFWRHijsrV0ffwzoJuQDz8k4ycWmpJ7NPqjQ0jR79WyOGG8aYv47GunheTotw6ci+uVspQW6U5RXNxPYRtxsLEhRgqO3Dya6Jd33Zq5FliahWubOngUUTUrsiWkY9nawwjj6RixxcWC4tun6s423KTlJuUn1b9QPdttttt7tvqwMyY+NGla7ye7NfP6Qnly0XKC2XnyiMVk3Ek5OcaqoOy6fywXr5fZeTPOcYRcpPRI06qp2zUILVsozc2rCq47N5Sk9oVx6zfZHJh4F2XkLLzdnP+SC+Wtdl+5fRo+RDNndqHxZPTl8sV2j4NeMFBbIxKxWJSi+R67o/o6OIuOfOfh8v5FhWoR2SC2FsVsk6DYGIwsRgqwMrYzYjYKMVlcmO2VsFGIyuQ8mVyBRiSKpFkiqRBRiMgH1ICh9cTHTKEx0zYPHlyYdypMZMA1NOzowisTJf3EuUJv+R9vy7djy3tn7JS47NQwq/vPmtrivnX4l57o1t01szU07OjZCOFlS+F8qrG+afon+n9jg5mNPDseXjLl+qP7r9/8AZlXtEk/eWz/ZnxUh7b2y9k5Y1lmfh1/D811cV/1x8d0eI3Opj5MMitWQfJlU9eT3CTcG4NzMSHcgCbgEIDcm4BAEACQnRi5Tolwy51vr48nMAENam8pKUU000/UjZlYmW6XwTf3b/wBDpvyrJXxxMOKtyprfn8tcfxS/RepdPUxtaBycqVc44+PD32VZ8le/JL8Un6L6+hu6BpstP4rb7Hddbztm19F6JdhdH0WGDBzk3ZfY+Ky2XWT/AN+hspKK5GO+mF1brsWqZlounTNWVvRovzMGvNoTi0ppbwl+n5HmrYTqslXNcMo8mj0VN/uns/kfXx5BqODHMq44bK1L4X38Hnqp2dG29VZzg9n5+68v2OHmRyYarftR5psRsacZQk4yTUk9mn6CNnfTTWqNpgbEbC2I2CjAxGwtiNgqxWxGM2VsFGK2VsZsrkwUYsmVSHkyuRBRiPqQDfMgKH1ZSLFI51IsTNg8iXJhTKkxlIEFu5Hs1s+jK9xtwDawM2OXXHDy5fedKrX6+H5+p4D2v9lZaddZm4le1G+9tcV8n/Mv+X6Hpd9zZxMuGpU/ZMpr7Qk+CbX8Rdn57o8/k0T6PseTQvZv3l3fFfDw+Rm/q/8AJff4P9j4kTc9N7V+zE9Jvnk41b+yt/FD/wAp/wDb2PLnXpuhdBTg9Uyieo24NwE3MpYO4Abk3ACDcG5NwCE3ALj0ZGp5LxcN8Ki9rb9t1Dwu8voBsCuF+dlfZMNJ2L+JY1vGpee78HttH0ajTcdQgm5PnOcucpPu2PpOkY+m40aqYbJc23zbfdv1Zp9C65GNvUnRAbA2K2ARstoyPdPhk/gfr2KGxGzBkY8MitwmZ8e+dE1OBdqenrKh72pJXJf+5HmpbptNbNcmmemxsng2rm/h9H2ObVdO9+nfSvvV80V/N/k4ePdZg2ej3+72Pz2eB7DHyIZNfHD6nn2xGwtitncMjA2VtjNlbZJVgbK2xmxGwUYsmVyYzZXJkFGLJlUmPJlcmCjFfUgrfMgKH1FSHUjnUh1IznkjoUhlIoUhlIAvUg8RSpDcRILNw8XNNNpp7pp80yviJuQ1ryYN6i+rWMaWNkKH2lRa5rlZH15fVHzH2m9nLNGyXbVGTxJS2W/Wt/hf6M9jGcoyjKMnGUXvGS6pmypUa7hzpuhB5CjtOD6WR/3/AGPP21z6Ms62vnU91/b+PAzadbzXveP5Piu4NzZ9odBt0XKbipSxZvaEn1i/wvz9TF3O1XZGyKlF8mUTTWqDuDcG5Ny5IdwA3HwMC7W7eGtyhhJ7TtXJ2eI+O7/sSlqG9BcPEv1fIdOM3CiL2tvX/wAY+fPoe603TaNPxoU01qEIrZJDYODTh0QqqrjCEVsklyR2bltjG3qHfYDYGxWwAtitgbFbBJGxWwNitkAjZ0Y2TttXN/8Apb+hyNiNmtlYsMmvgl9H3Gzi5U8azjj9V3k1XTuPfIoj8XWcV6+TBbPUY+Txfdzfxej7mZqun8O+RSuXWcV6eTk4uRPGn6Nf9H57PA9bVdC+CsgY7YjYWxGztBsDYjYWytsgowNlcmM2VtgoxZMrkxpMrkwUbFb5kA2QFT6UpDqR5n2e9pI6snRkKFWZHd8MflnHvH8vVf1PQqRmTPKtaHSpDKRzqQ6kSQXqQykUKQ3ECC7iJxFSkHiALdxoWzqsjZXJxnF7xkvQp4icREoqScZLVMLVc0bl1eN7Q4FkLKou3h2tqf8AMu6/RnyrXNFu0bL4Jbyom37uzbr4flHvKr7KLo3VS4Zx6P8AR+DTzcXE9otNs3rTk1tbV6p9157M4DjLoyzlzql/1/Hd/pmdrrfXj73au/4r4+J8c3A2km29kurO3WdMt0bJlXc96tnKFr5KUf0a9SrS9Is1ecbb4yhhJ7xra2dvl9o+PX1O5CSmtY7GPiWmomm6ZbrdilJShgLq+ju/aP1/I93i4teNVGEIqMYrZJLoTHohTBRikkuXIv3MhTcbcVsXcDYJGbFbFbA2AFsVsDYrZAI2K2BsVsAjYjZGxGwSRs6qMn3i4J/P9TibEctuaezXY1MvFhkw0e/YzbxMuePPVbdqKNTwPct3Ur7t/Ml/L/gymz01WQrouE9uLbmu6MXUcJ48nZWvun/0mhiZM65ej37rbz4Hp42RtipwfJme2I2FsRs6hVsDZW2FsrbBVsEmVthbEkwY2wN8yCN8yArqY9Vs6bYW1TlCyD4oyj1T7n0b2e9oIavR7u3hhmVr44LpJfij48eh813LaMi3Gvrvosddtb4oyXoWT0PNtan2JSHUjC0LXKtXxd+UMmtL3te/TyvD/wAGupGQxtHQpDKRQpDKQILuIPEU8QeIkF3EHiKeIPEAW8QP+JLSlLOldCmFUd5ym9o8PZnJl5uPgYs8nJtVdUFzk/8ARJerfojz8a8nXsqGVnVurFrlxY+I/R+k595eOi/PmUnCNkXCS1TJTcXqjr1HKs9sM6ORbjSxtLrkp04018Vsvxz7eI/1fPprU1RriklsLVXGuOyRZuVpphTBVwWiQk3JuT7R9wbibg4jIQO5CtiuQvEAO2K2K2K2AM2K2K2K5AkZsRsDYjYAWxGyNiNkAjYjZHIrcgSHiaaaezXRnTC6N8HGSW+3xR7nE2JxuMlJPZo08vEjkR/yWxuYeXLHl/i9zkz8R40+KPOp9H28HA2eiVkMipxklzW0omHm40sazlzg/lZq4mTLXqbfeR6FSjOKlHZnM2VyYZMrbOiUbA2VyYZMRsFWwNkEb5kBQxtw7le4yZJ586cTLvwsmvIx58FsHyfo+6fdM+kaPrFOrYnva/htjytqb5wf6p+jPl+5qaCs7/ilc8DlOPzuXy8PqpeCUyslqfT1IZSOSu3jXTaS6rsWqRkMZ0cQeIoUhuIAu4jnztQx9OxZZGTPhgnsklvKcn0jFerZz6hqdGm43vbm25PhrrhzlZLtFf72MnFxMjPy1n6js7ktqqoveFMX6Lu+79fyAHox8nVsuGdqMeFQe9GNvvGry+8/Pp0RvVxUEkiqCUFsh+IEFvEDiK+InEAWcQOIr4gcQJLHIVyE4gOQA7kK5CuQrkAM2K5CuQrkQBnIRyA5COQJGchHIDkI5ABcityI5CNkEkbEbA5CNgBU3CXFF7NF0pV5NLjJbp8muxyNiqxwlxR6/U0svFVy4o+8jdxMp0vR+6zgyqJY9nC+cX8r7nM2blnu8mlp9H/dMxMiqVFjjL+j7mPFyHP1LPeR2201qtipsrbC2VyZuGNsm5BGyArqY2425WMuhY4R36dp12pZCrqW0V8830ij3un4dGn4ypojsvWT6yfdnJplNdGBVGqCinFN7erO+LLJaGNvUvbe6lF7SX+vhltdqmt+jXJp+jOZMm7V1bX8zaflbFip2qRyahqdWn1Rcoysus5VUw+ax/ou79C+PVGLgQjfddlWrjvlOUHN9VFPZJdl+QA+FhXX5Lzs+SsypLZJfLVH8MfHnqzahtFcimHKJZuCC3iJxFQdwCziJxFe4N3sAWcQOIQG/IAfiA5CbgAHchXIVsG4JGchXIVisALkK5AYjIAXIRsj6iMEhcityIxGARyEkySZXIgkjkVyYWVsAMbHXLdf1Xce6MMmnZ9PR+qZzsNLfvNt+TXM0cuhNdbHk0b+HkSjLq3szMuhKqbjLqv9SiTNXPinjuTXNPkzIZfHtdkNXudOQGyCkM5TU//Z";

    private final static String[] imgList = {"http://img3.imgtn.bdimg.com/it/u=1592877738,3666022423&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3455823481,3036827216&fm=21&gp=0.jpg",
            "data:image/jpeg;base64,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",
            "http://img3.imgtn.bdimg.com/it/u=270179915,2007129802&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=1255836822,4097950891&fm=21&gp=0.jpg"};

    private final static String headerUri = "http://vinos.b0.upaiyun.com/avatar/default/0247662e-21bf-4662-89fd-d7ebc0600a4e.jpg";

    private RVAdapter rvAdapter;
    private RecyclerView rvRedWine;
    private List<ProductBean> productBeanList = new ArrayList<>();

    private SimpleDraweeView sdvDrawerHead;
    private SimpleDraweeView sdvUserHead;
    private DrawerLayout layoutDrawer;
    private NavigationView layoutNavigationView;

    private List<Fragment> fragmentList;
    private ViewPager staggerViewpager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_wine);
        title = "红酒";
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        initView();
        initData();
    }

    private void initView() {
        rvRedWine = (RecyclerView) findViewById(R.id.rvRedWine);

        layoutDrawer = (DrawerLayout) findViewById(R.id.layoutDrawer);
        layoutNavigationView = (NavigationView) findViewById(R.id.layoutNavigationView);
        staggerViewpager = (ViewPager) findViewById(R.id.staggerViewpager);
        fragmentList = new ArrayList<>();
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragmentList);
        initMainData();
    }

    private void initData() {
        rvAdapter = new RVAdapter(this, productBeanList);
        rvRedWine.setHasFixedSize(true);
        rvRedWine.setLayoutManager(new GridLayoutManager(this, 2));
        rvRedWine.setAdapter(rvAdapter);
        layoutNavigationView.inflateHeaderView(R.layout.layout_drawer_head);
        sdvDrawerHead = (SimpleDraweeView) layoutNavigationView.getHeaderView(0).findViewById(R.id.sdvDrawerHead);
        sdvDrawerHead.setImageURI(Uri.parse(imageUri));
        sdvDrawerHead.setAspectRatio(1.33f);
        sdvUserHead = (SimpleDraweeView) layoutNavigationView.getHeaderView(0).findViewById(R.id.sdvUserHead);
        sdvUserHead.setImageURI(Uri.parse(headerUri));
        sdvUserHead.setAspectRatio(1.0f);
        layoutNavigationView.inflateMenu(R.menu.menu_drawer_nav);
        onMenuCheck(layoutNavigationView);
    }

    private void initMainData() {
        staggerViewpager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragmentList));
        staggerViewpager.setCurrentItem(0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.HTTP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getMainData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getMainResponse -> {
                    Log.d("redwine", "请求成功：" + getMainResponse.state);
                    Log.d("redwine", "请求成功：" + getMainResponse.data.banner.get(0).id + "数组长度：" + getMainResponse.data.banner.size());
                    Log.d("redwine", "请求成功：" + getMainResponse.data.products.get(0).cn_name);
                    productBeanList.addAll(getMainResponse.data.products);
                    rvAdapter.notifyDataSetChanged();

                    Observable.just(getMainResponse.data.banner)//相当于.next
                            .observeOn(AndroidSchedulers.mainThread())//观察在主线程
                            .flatMap(Observable::from)
                            .subscribe(LambdaRedWineActivity.this::bindViewpager);
                }, throwable -> {//必须是抛出异常的方法
                    Log.d("redwine", "异常：" + throwable.toString());
                });
    }

    private void bindViewpager(BannerBean bannerBean) {

        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("img", bannerBean.img);
        viewPagerFragment.setArguments(bundle);
        fragmentList.add(viewPagerFragment);
        staggerViewpager.setAdapter(pagerAdapter);
        Log.d("lambda", "fragmentList长度：" + fragmentList.size());
    }

    private void onMenuCheck(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_menu_home:
                    Toast.makeText(LambdaRedWineActivity.this, "点击第一个", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_menu_categories:
                    break;
                case R.id.nav_menu_feedback:
                    break;
                case R.id.nav_menu_setting:
                    break;
            }
            item.setChecked(true);
            layoutDrawer.closeDrawers();
            return true;
        });
    }

    class PagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public PagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
