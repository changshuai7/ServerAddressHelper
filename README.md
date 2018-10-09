本项目主要方便于对服务器地址的管理

在实际开发中，可能会遇到后台服务器地址在线上环境、测试环境、开发环境等条件下地址错综复杂，端口随时变动的情况。未解决此类问题。封装了本库，通过本库可以条理清晰地管理服务器地址。
# 集成： #
	Step 1：Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}

	Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.changshuai7:ServerAddressHelper:1.0.0'
	}



# 使用： #

## 1.在Application中初始化： ##

    ServerHelper.init(this, BuildConfig.DEBUG)//传入debug的boolean值
		.addServerAddress("KEY_1",
			new AddressBean[]{
				new AddressBean("ADDRESS_KEY_0", "http://release.key1.address0.com", "", "Release第1个"),//第一个添加的一定是线上地址
				new AddressBean("ADDRESS_KEY_1", "http://debug.key1.address1.com", "1111", "Debug第1个")
			})
		.addServerAddress("KEY_2",
			new AddressBean[]{
				new AddressBean("ADDRESS_KEY_0", "http://release.key2.address0.com", null, "Release第1个"),
				new AddressBean("ADDRESS_KEY_1", "http://debug.key2.address1.com", "3333", "Debug第1个"),
				new AddressBean("ADDRESS_KEY_2", "http://debug.key2.address2.com", "4444", "Debug第2个"),
				new AddressBean("ADDRESS_KEY_3", "http://debug.key2.address3.com", "5555", "Debug第3个")
			})
	....


**API: addServerAddress(String key, AddressBean[] value)**

String key：服务器类型标识，一般一个生产环境地址对应一个类型；

AddressBean[] value：非生产环境下地址的集合

	AddressBean:

	addressKey:地址标识,每种服务器类型下，可以对应多个地址

	addressHost:服务器地址

	addressPort:端口号

	addressDescription:描述，会展示到弹窗的Spinner显示中。

## 2.在启动的Activity中弹出服务器选择弹框： ##

	ChooseServerEnvDialog.showDialog(MainActivity.this, new ChooseServerEnvDialog.chooseResult() {
	    @Override
	    public void onConfirm() {
	       	//code..弹框选择完成，写你接下来的逻辑	
	    }
	});
	

<img src="https://github.com/changshuai7/ServerAddressHelper/blob/master/screenshots/Screenshot_1.jpg"  height="640" width="350">

服务器地址带有记忆功能，第二次进入会默认选中上次的地址

弹框支持横屏
## 3.使用服务器地址 ##

	 //获取服务器的完整地址
	 //第一个参数：服务器类型标识；第二个参数：拼接字段
	 ServerHelper.getAutoCompleteServerAddress("KEY_1", "/rrc/sale/demo")；

# 注意： #
1.在添加地址时候，AddressBean[]第一个地址一定是线上地址

2.只有在debug模式下才会弹出选择服务器环境（不能选择线上地址），release模式下直接是走线上地址

3.一般不需要给线上地址设置端口号（传入null或者空字符串即可）。端口号默认是80，如手动设置了，则会采取设置的端口号。


没有了复杂的地址管理，是不是变得非常的简单？快来使用吧！