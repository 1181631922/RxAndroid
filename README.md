# RxAndroid
RxAndroid+Retrofit示例代码

# 先看一下观察者模式：
定义对象间的一（被观察者）种一对多（观察者）的依赖关系，当一个对象发生改变时，所有依赖于他的对象都能得到通知并被自动更新。<p>
![观察者模式示意图](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/B00D11AD-7B97-42A2-A04E-397256D75283.png)
<p>
# RxAndroid的观察者模式
Rxandroid的观察者模式有三个回调方法（如果结合retrofit的话里面的参数是可选的，但是nonext是必选的，举个例子，如果用户不关心结果，
不关心事情是否做完，只要有个onnext通知就可以，但是一般涉及到网络的话还需要关心onerror结果，做事情么，要的就是结果，
但是oncompleted如果在批处理我感觉没啥卵用，后文会有代码说明），这三个onnext是一定触发的，剩下的两个有其中一个也是会一定触发的。<p>

# 正文
* 先看一个简单的打印<p>
![helloworld的简单打印](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/FE9623A7-D9FB-468F-A82E-1B89F98E9D6B.png)<p>
* 是不是感觉代码变多了，再结合一下lambda表达式：
![lambda表达式helloworld](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/D451C372-C076-4102-8960-348790B136B6.png)<p>
是不是有变简洁了，这个可以类比as的默认缩进

# 来看一下源码，正好帮助自己理解一下
![rxandroid观察者源码](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/76EA1EC5-0ADC-4F9C-A39B-44F00905B2FB.png)<p>
* 这个empty很有意思，来看下源码，这里采取的是单例的设模式
![empty源码](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/DCDAE7B2-EA65-40CC-BEEF-459E159BB053.png)<p>
* 再看一下onxxx里面的核心代码，你就知道为啥可以遍历了
![onxxx核心代码](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/F1DD21F2-F806-407C-94E7-0966F7279E4D.png)<p>
看了下稍微有点失望，其实也是用的for，但是再去具体看的话里面又结合了观察者模式还有三个回调方法，这里建议大家自己看一下源代码。

# 这里来看结合实例理解一下观察者和订阅者
![观察者](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/D5C020A9-FE11-42AE-8EB7-9CE8EEB43FA1.png)<p>
![订阅者](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/7559AD58-4377-44CC-AFCA-CD0741B77DC0.png)<p>
* 这有段挺不错的代码，截取了一下
![示例1](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/52A85415-6ED3-4848-A522-FFF243276FFF.png)<p>
![示例2](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/FBB2E751-8164-4535-A3C7-D69B2A5DD33A.png)

# 该说的基本都说了，现在你的心里估计还是一脸困惑，心里肯定再说有个毛用，先拿最简单的helloworld做个例子
* 看一下实例
![简单实例](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/0B266737-8636-40FD-AF68-864D866C08B4.png)<p>
* 其实这时候大家所想估计和我当时一样，心头一万头草泥马路过，一行代码的问题这么多代码才搞定，一点都不简洁，ok，来看点简洁的
![简洁示例](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/CA87997B-BEBF-46AF-A0F6-A7CCBDA0EC03.png)<p>
![操作界面](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/0C1C3A0F-5B85-4812-8B04-3EFC8E97BA8D.png)<p>
* 这里又有问题了，你肯定会问hello哪去了，到了这里是不是有那么一点感到还行哈，变简单了，这里看一下just的源码，其实到了from那里，这里好多用的都是泛型，
而且也必须用泛型，这样使用者考虑的就不必那么多，不必拘谨了，ok，连贯的打印所需要的东西
![测试打印](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/3457B59F-27EC-4D62-8238-1A3A0DA44575.png)
* 关于县城切换的都有注释，下面再举两个例子，看一下io，newthread的区别，因为网络请求这两个都可以，然后就有点好奇了，结果其实我感觉差别不大，至少在创建的时候
![区别1](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/54023446-081C-4CEB-8378-C0FC64626CD9.png)<p>
![局别2](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/8A35451F-4BB5-4FB0-BC5C-2844ADDED775.png)<p>
这个包是rxjava里面的，其实io和newthread创建过程是一样的，但是prefix不一样，还有incrementandget也不一样，然后去里面翻了翻，结果是这样，也是意料之中吧，
但是感觉写这个代码的人对性能要求挺高的，注意标红箭头的
![prefix](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/44197474-81E1-4FD7-AD8D-0D432F71F682.png)

# 来看一些实例
![operate3](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/0ECE12B0-6DE5-4E42-9E33-8610D8A1A329.png)<p>
![operate4](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/0095E10C-DEC2-41EE-ADC3-9B4C041EA10D.png)<p>
线程切换是不是很方便，还有就是感觉rxandroid在批处理方便很方便，最大的方便就是不用自己去写一堆循环了。
* 下面来看一下lift()线程变换的原理
![lift变换](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/344EE99E-88D4-47F3-9500-B1F05B5EC05D.png)<p>
![call](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/01CEA1B0-474C-4C9A-9CBD-C8603FE72C8F.png)<p>
又仔细瞅了瞅，感觉这个方法很方便，而且根据方法名字就能知道他是做啥的，operator大家可以自己看源码。

# 到此为止RxAndroid可以进行数据的处理了，再来就是加上网络请求，这里是和retrofit相结合的，rxandroid自己也可以，但是需要自定义callback，这里不细说了。
从项目的应用角度说一下retrofit，其实就是okhttp的一个包装。
retrofit有好多大神讲的很好了
* [http://square.github.io/retrofit/](http://square.github.io/retrofit/)
* [http://blog.csdn.net/lmj623565791/article/details/51304204](http://blog.csdn.net/lmj623565791/article/details/51304204)
* [http://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650236203&idx=1&sn=54938620ba067eb3d6224dacb9fcaa2e&scene=23&srcid=07054QMyYdc11tfSunhoYX7m#rd
](http://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650236203&idx=1&sn=54938620ba067eb3d6224dacb9fcaa2e&scene=23&srcid=07054QMyYdc11tfSunhoYX7m#rd
)

# 再有就是对网络请求的数据解析
* 这里使用的是gson，如果不进行注解的话，bean的字段需要与服务器返回的字段相同，并且类型需要对应
![getdata](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/5626D7DA-B433-4AC4-BE5B-CA640603BA8E.png)
* 线面说一下rxandroid+retrofit，不过在这之前还是先了解一下拦截器的问题，一般请求的header是不会变得，但是有的是动态加的，这里做两个测试
![一](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/5BEF40E1-982E-4C44-9B2B-225AEF906430.png)<p>
![二](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/59AB5EBD-FF3B-49F0-B677-0586222E88B1.png)<p>
* 现在来看一下网络请求的流程
![网络请求](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/7C25388E-9925-4298-8525-46320E06E61D.png)<p>
* 光看是不是就感觉很爽，一条线下来全部搞定，其实还可以再简洁一点
![initdata](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/1EB5BD9B-E541-4130-8887-EF26240FC94A.png)<p>
* 到这里基本就完了，之后就能上手项目里



