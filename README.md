# YQHSupport
### 这是我和我的开发团队正在使用的一套开发框架，能够帮助你快速的构建一个应用而屏蔽了一些复杂的版本实现，目前来说提供了一些Activity，Application，Dialog，Http，IO，Log，Notif，Permissions和Thread，还提供了一些其他的功能，比如Cache，跨进程的Cache等，可以帮你迅速地完成你的任务，就按字典顺序慢慢来吧。。。。。


### 一、Activity：我好像不用介绍这是什么、、、
``` java
@StateBar
public class Activity extends YActivity{
}
```
1、有个@StateBar注解在了这个Activity上，这是这个注解类的源代码：
``` java
@Target(ElementType.TYPE)  
@Retention(RetentionPolicy.RUNTIME)  
public @interface StateBar {  
  /**  
    * 状态栏的颜色  
    **/  
  int color() default 0xffffffff;  
  /**  
    * 状态栏字体是否为深色  
    **/  
  boolean fontDark() default true;  
}
```
看注释就明白，这个注解的作用就是改变当前Activity的状态栏颜色和字体颜色，默认的就是状态栏为白色，字体为黑色。当然，前提是你得继承YActivity这个类。

2、YActivity的继承规则如下：
``` java
	  YActivity				//这个类转供上层继承使用，无实现代码
	      ↓
YActivityResultActivity		//这个类封装了Activity返回到上一个Activity的返回值的传递问题
		  ↓
  YImageLoadActivity		//这个类封装了从本地相册或者照相机获取图片的方法
		  ↓
  YPermissionActivity		//这个类封装了权限的申请方法
		  ↓
	YBaseActivity			//这个类实现了IYActivityInterface接口里的所有方法
		  ↓
   AppCompatActivity
```
2.1、YActivityResultActivity：致力解决Activity返回值问题
通常我们使用 startActivityForResult 等方法启动一个Activity肯定会期待他带回来一个返回值给我用。。。一般的做法是重写 onActivityResult 来判断很多参数，如果期待的多了。。肯定会乱，这个类封装这个方法改为使用注解修饰方法的方式来调用，比如：
``` java
public class TestActivity extends YActivity{

	public static final int STARTCODE_TEXTACTIVITY=100;

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//...
		 startActivityForResult(Intent(this,TestActivity2),STARTCODE_TEXTACTIVITY);
	}
	
	@ActivityResultCallback(STARTCODE_TEXTACTIVITY)
	public void callBackTestMethod(int resuleCode,Intent intent){
		//在这里面处理Activity返回给你的值
	}
}
```
使用注解 ActivityResultCallback 来修饰一个方法，其中注解的参数就是Activity的StartCode，**修饰的方法必须接受int和Intent这两个参数**。

2.2、YImageLoadActivity：致力解决从本地相册或者相机获取图片的复杂过程
从本地相册和照相机获取图片是个复杂而头疼的问题，比如：
``` java
Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
intent.putExtra(MediaStore.EXTRA_OUTPUT,$cameraResultURI);  
startActivityForResult(intent,$REQUEST_CAMERA);

//再比如

Intent intent=new Intent();  
intent.setAction(Intent.ACTION_GET_CONTENT);  
intent.setType("image/*");  
startActivityForResult(intent, $REQUEST_STORAGE);

//然后监听Activity的返回值，还需要做Uri和String的转换等等。。
```
而在继承YActivity中只需要调用方法 loadBitmapForCamera / loadBitmapForStorage 这两个方法既可以从相册和本地文件中获取到图片，例如：
``` java
public class TestActivity extends YActivity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//....
		loadBitmapForCamera();
	}
	
	@Override
	protected void onImageLoadResult(Uri imageUri,String imagePath){
		
	}
}
```
重载 onImageLoadResult 这个方法，当调用 loadBitmapForCamera / loadBitmapForStorage 时会将你选择的或者拍摄的图片的Uri和StringPath传递给这个方法。

2.3、YPermissionActivity：致力解决于权限调用起来混乱的类
惯例，在Android6.0+(23+)中加入了动态权限，调用的时候需要判断版本号，然后使用ContextCompat等等很多方法申请权限，然后在重载onRequestPermissionsResult这个方法去判断，过程比较复杂，稍有不慎就会发生很多问题。。。而YActivity解决了这个问题，例如：
```java
public class TestActivity extends YActivity{

	public static final int REQUEST_PERMISSION_CODE=1;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//....
		checkCallPermission(REQUEST_PERMISSION_CODE,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
	}

	@PermissionsCallback(REQUEST_PERMISSION_CODE)
	public void startHomeActivity(){
		//当权限申请成功会执行这个方法
	}

	@Override
	protected void onCheckCallPermissionFailure(int callBackMethodID,String[] permissionArray){  
		//当请求的权限被拒绝的时候会传到这个方法里，同时会将你传给checkCallPermission这个方法的参数传给你
	}
	
}
```
使用 checkCallPermission 这个方法申请权限，其中参数一是申请的ID，参数二是要申请的权限数组，如果全部申请通过了，会根据参数一指定的ID查找被PermissionsCallback注解修饰的方法，而且注解的值和申请的ID相等的情况下回调用这个方法，如果有任何一个权限被拒绝了都会调用onCheckCallPermissionFailure这个方法并将失败的权限传到参数二中。

2.4、YBaseActivity：这个类实现了IYActivityInterface接口里的所有方法，如下：
```java
/**  
  * 加载图片到ImageView  
  * @param url 图片地址路径  
  * @param imageView 要被加载的ImageView  
  * */
void loadBitmapToImageView(String url, ImageView imageView);  
/**  
  * 加载图片到ImageView  
  * @param uri 图片uri路径  
  * @param imageView 要被加载的ImageView  
  * */
void loadBitmapToImageView(Uri uri,ImageView imageView);  
/**  
  * 启动一个子线程  
  * @param runnable 代码执行器  
  * */  
void startThread(Runnable runnable);  
/**  
  * 启动一个UI线程  
  * @param runnable 代码执行器  
  * */  
void startUIThread(Runnable runnable);  
/**  
  * 向进程缓存中添加数据  
  * @param key 缓存数据的KEY  
  * @param value 缓存数据的Value  
  * */
void cachePut(String key,String value);  
/**  
  * 从进程缓存中读取数据  
  * @param key 缓存数据的Key  
  * @return 获取到的缓存内容，如果没有缓存则为null  
  * */
String cacheGet(String key);  
/**  
  * 修改进程缓存中的缓存数据  
  * @param key 要修改的数据的Key  
  * @param value 要修改的值  
  * @return 如果修改成功则返回原始的值，否则返回null  
  * */
String cacheSet(String key,String value);  
/**  
  * 移除进程缓存数据中的值  
  * @param key 值的Key  
  * @return 如果进程缓存中有这个值则返回删除了的值，否则返回null  
  * */
String cacheRemove(String key);  
/**  
  * 创建一个基础对话框，详见{@link BasisDialog}  
  * @return 创建好的对话框  
  * */  
BasisDialog createBasisDialog();  
/**  
  * 创建一个基础对话框，并设置标题，详见{@link BasisDialog}  
  * @param title 对话框的标题  
  * @return 创建好的对话框  
  * */  
BasisDialog createBasisDialog(String title);  
/**  
  * 创建一个基础对话框，并设置标题和消息，详见{@link BasisDialog}  
  * @param title 对话框的标题  
  * @param message 对话框的消息内容  
  * @return 创建好的对话框  
  * */  
BasisDialog createBasisDialog(String title,String message);  
/**  
  * 展示一个基础对话框并设置消息，通常用作给用户一个简单的提示，这个对话框会带有  
  * 一个Ok按钮，即关闭按钮，详见{@link BasisDialog}  
  * @param message 要显示的消息  
  * */  
void showBasisMessageDialog(String message);  
/**  
  * 创建一个选择对话框，同时设置对话框的标题和内容，详见{@link SelectDialog}  
  * @param title 对话框标题  
  * @param item 变参，对话框的值列表  
  * @return 返回创建好的对话框  
  * */  
SelectDialog createSelectDialog(String title,String ...item);  
/**  
  * 创建一个post请求，详见{@link com.yuqianhao.support.http.IHttpRequestAction}  
  * @param requestBody 请求参数  
  * @param RequestResponse 请求结果  
  * */  
void post(IHttpRequestBody requestBody, IHttpRequestResponse RequestResponse);  
/**  
  * 创建一个get请求，详见{@link com.yuqianhao.support.http.IHttpRequestAction}  
  * @param requestBody 请求参数  
  * @param RequestResponse 请求结果  
  * */  
void get(IHttpRequestBody requestBody,IHttpRequestResponse RequestResponse);  
/**  
  * 将数据写出到文件  
  * @param path 要写出的文件路径  
  * @param data 要写出的数据  
  * */  
void writeData(String path,String data) throws IOException;  
/**  
  * 从文件中读取数据  
  * @param path 文件的路径  
  * @return 读取到的数据  
  * */  
String readData(String path) throws IOException;  
/**
  * 这一组是Log相关的，会在Log处介绍。
  * */
void logDebug(String msg);  
void logWarn(String msg);  
void logInfo(String msg);  
void logError(String msg);  
void logDebug(String msg, Exception e);  
void logWarn(String msg, Exception e);  
void logInfo(String msg, Exception e);  
void logError(String msg, Exception e);  
/**
  * 显示一个短时间的toast
  * @param msg Toast的内容
  * */
void showToast(String msg);  
/**
  * 在窗口的顶部显示一个背景为绿色的信息框，持续1500ms
  * @param msg 显示的内容
  * */
void showSuccressNotifyMsg(String msg);  
/**
  * 在窗口的顶部显示一个背景为红色的信息框，持续1500ms
  * @param msg 显示的内容
  * */
void showErrorNotifyMsg(String msg);  
/**
  * 设置状态栏颜色为浅色，即字体为黑色
  * @param color 状态栏颜色
  * */
void setStateBarColorBright(int color);  
/**
  * 设置状态栏颜色为深色，即字体为白色
  * @param color 状态栏颜色
  * */
void setStateBarColorDark(int color);  
/**
  * 显示一个进度对话框病设置内容
  * @param message 对话框的内容
  * */
void showProgressDialog(String message);  
/**
  * 修改当前正在显示的对话框的内容
  * @param message 对话框的内容
  * */
void setProgressDialogText(String message);  
/**
  * 关闭正在显示的对话框
  * */
void closeProgressDialog();
```
### 二、Actuator：这是一个线性的异步执行器，可以线性的执行一大堆事件，然后等待这所有的事件返回并且可以处理所有的返回值。
直接上代码：
```java
public class TestActivity extends Activity{
	public void onCreate(Bundle savedInstanceState){
		//...
		YLog.logInfo("onCreate::Start");
		LinearActuator mLinearActuator=new LinearActuator();
		mLinearActuator.pushBack(new IActuatorRunnable(){
			int id(){
				return 1;
			}
			
			Object exec(){
				YSystemNativeV0.callLinuxSleep(3000,null,false,false);
				return "这是第一个事件";
			}
			
		});
		mLinearActuator.pushBack(new IActuatorRunnable(){
			int id(){
				return 2;
			}
			
			Object exec(){
				YSystemNativeV0.callLinuxSleep(3000,null,false,false);
				return "这是第二个事件";
			}
			
		});
		mLinearActuator.pushBack(new IActuatorRunnable(){
			int id(){
				return 3;
			}
			
			Object exec(){
				YSystemNativeV0.callLinuxSleep(3000,null,false,false);
				return "这是第三个事件";
			}
			
		});

		mLinearActuator.setCompleteListener(new IActuatorCompleteListener(){
			void complete(List<ActuatorValue> actuatorValues){
				for(ActuatorValue tmpActuatorValue:actuatorValues){
					YLog.logInfo(actuatorValues.id()+" "+(String)actuatorValues.getValue());
				}
			}
		});
		mLinearActuator.start();
		YLog.logInfo("onCreate::End");
	}
}
```
Console：
```java
onCreate::Start
1 这是第一个事件
3 这是第三个事件
2 这是第二个事件
onCreate::End
```
LinearActuator 是一个线性的执行器，线性指的是会按顺序投放到线程池中执行事件，但是不不一定谁会先执行完毕，提供的函数如下：
```java
/**  
  * 向执行器队列中添加一个执行事件  
  * @param runnable 执行事件  
  * */  
void pushBack(IActuatorRunnable runnable);  
/**  
  * 获取执行队列的数量  
  * */  
int size();  
/**  
  * 启动队列  
  * */  
void start() throws InterruptedException;  
/**  
  * 判断队列是否正在执行  
  * */  
boolean canRunning();  
/**  
  * 注册队列执行完毕获取所有执行事件返回值的回调接口  
  * @param completeListener 回调接口  
  * */  
void setCompleteListener(IActuatorCompleteListener completeListener);
```
其中pushBack向事件队列添加一个事件，这个事件是IActuatorRunnable的实例，定义如下：
```java
/**  
  * 执行的id
  * */
int id();  
/**  
  * 将要执行的代码写在这个方法中，其中返回值回最终通过{@link IActuatorCompleteListener#complete(List)}这个方法返回  
  * */  
Object exec();
```
**※值得注意的是，id() 这个方法在同一队列中不能重复，否则可能你会在ActuatorCompleteListener#complete(List)中无法精确找到返回值。**
如果想要获取执行队列的返回值，可以使用setCompleteListener这个方法设置一个监听器，来获取所有的返回结果，但是要注意，当所有的事件执行完了才会调用这个方法传给你结果，定义如下：
```java
/**  
  * 当执行器执行队列中的所有事件执行完毕则回将所有的结果传给这个方法  
  * @param actuatorValues 执行器的返回结果集，可以使用id区分  
  * */  
void complete(List<ActuatorValue> actuatorValues);
```
你会发现执行结果通过ActuatorValue这个类包装了起来，提供的函数如下：
```java
//获取这个结果的ID
public int getId();
//获取执行完毕之后的结果，这里获取到的是Object
public Object getValue();
//获取执行完毕之后的结果，这里获取到的是任意类型，比如：
//String str=actuatorValue.castValue();
//Double double=actuatorValue.castValue();
//前提是你确实返回了这个对象
public <_Tx> _Tx castValue();
```
每一个IActuatorRunnable独享一个子线程，主线程等待这所有的事件执行完毕后继续执行。如果不想在主线程中等待就可以在子线程中start这个事件队列。
### 三、YApplication
YApplication实现了Thread.UncaughtExceptionHandler和ILogForwarding这两个接口，其中最后一个接口是全局的Log捕获接口，会在YLog处介绍，同时提供了一下几个方法可供重载：
```java
//是否将全局RuntimeException异常处理器交给Application，如果返回true则所有的RuntimeException都会转交给uncaughtException这个方法处理，同时不会再出现那个“停止运行”的小框框。
//默认为false
protected boolean canOpenSetDefaultUncaughtExceptionHandler(){}
/**
  * 如果APP有多个进程，这个方法会被不容的进程调用，并且会将进程的名字传给这个参数
  * @param processName 进程名
  * */
protected void onStart(String processName){}
//这个就不介绍了，官方文档多得很
public void uncaughtException(Thread t, Throwable e){}
/**
  * 这个是YLog相关的，凡是YLog打印的日志都会最终交给这个方法处理，你可以在这里捕获并处理所有的Log日志
  * @param level 日志的级别
  * @param javaClassName 打印日志的类实例
  * @param logMsg 日志内容
  * @param statckMsg 如果当前有Exception发生或者打印，在这里可以获取到那个异常的栈调用轨迹
  * */
public void onLogProcessor(int level, Class javaClassName, String logMsg, String statckMsg){}
//返回YLog打印日志的时候的过滤名称，默认为APP的包名
public String onLogCatFilterName(){}
//是否打印YLog的日志，如果为true则会向控制台打印
//默认为true
public boolean canPrintLog(){}
//是否将YLog的日志传给onLogProcessor处理
//默认为true
public boolean canForwarding()
```
### 四、Cache：这个里面提供了一个单进程缓存框架，跨进程缓存框架，还有JSON反序列化对象的工具类。
这个缓存部分主要提供了以下几个类：
```java
1、CacheHelpManager		：单进程Cache的接口管理类
2、InjectionCacheHelper	：JSON和对象反序列化和序列化的工具类、
3、SharedStorgeManager	：进程共享的Cache接口管理类
```
1、CacheHelpManager：可以用过这个类获取到一个单进程的Cache的接口实例，例如：
```java
public class TEstActivity extends YActivity{
	public void onCreate(Bundle savedInstanceState){
		//、、、
		ICacheAction<String> cacheInterface=CacheHelpManager.bindCacheHelperService(this);
	}
}
```
ICacheAction提供了如下个函数：
```java
/**
  * 获取缓存中的值
  * @param key 值的key
  * @return 获取到的值，如果没有则为null
  * */
_Tx get(String key);  
/**
  * 向缓存中添加新的键值对
  * @param key 值的key
  * @param obj 值
  * @return 添加成功则返回true
  * */
boolean put(String key, _Tx obj);  
/**
  * 修改某个键值对的值
  * @param key 值的key
  * @param obj 要修改的值
  * @return 修改成功则返回修改之前的值
  * */
_Tx set(String key, _Tx obj); 
/**
  * 移除某个键值对
  * @param key 
  * @return 移除成功则返回被删除的值
  * */ 
_Tx remove(String key);
```
# 未完待续。。。