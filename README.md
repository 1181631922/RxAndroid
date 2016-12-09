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
<p>
# 来看一下源码，正好帮助自己理解一下
![rxandroid观察者源码](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/76EA1EC5-0ADC-4F9C-A39B-44F00905B2FB.png)<p>
* 这个empty很有意思，来看下源码，这里采取的是单例的设模式
![empty源码](https://github.com/1181631922/RxAndroid/blob/master/ScreenShots/DCDAE7B2-EA65-40CC-BEEF-459E159BB053.png)<p>
* 再看一下onxxx里面的核心代码，你就知道为啥可以遍历了
![onxxx核心代码]()