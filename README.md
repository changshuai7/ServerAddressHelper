本项目主要方便于对服务器地址的管理

在实际开发中，可能会遇到后台服务器地址在线上环境、测试环境、开发环境等条件下地址错综复杂，多个debug地址，端口复杂变动等情况。未解决此类问题。封装了本库，通过本库可以条理清晰地管理服务器地址。
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
		//添加第一类服务器地址
		.addServerAddress("KEY_1",
			new AddressBean[]{
				new AddressBean("ADDRESS_KEY_0", "http://release.key1.address0.com", "", "Release地址"),//数组中第一个添加的一定是【Relase线上地址】
				new AddressBean("ADDRESS_KEY_1", "http://debug.key1.address1.com", "1111", "第1个Debug地址")//添加Debug测试环境地址1
			})
		//添加第二类服务器地址
		.addServerAddress("KEY_2",
			new AddressBean[]{
				new AddressBean("ADDRESS_KEY_0", "http://release.key2.address0.com", null, "Release地址"),//数组中第一个添加的一定是【Release线上地址】
				new AddressBean("ADDRESS_KEY_1", "http://debug.key2.address1.com", "3333", "第1个Debug地址"),//添加Debug测试环境地址1
				new AddressBean("ADDRESS_KEY_2", "http://debug.key2.address2.com", "4444", "第2个Debug地址"),//添加Debug测试环境地址2
				new AddressBean("ADDRESS_KEY_3", "http://debug.key2.address3.com", "5555", "第3个Debug地址")//添加Debug测试环境地址3
			})
	    //以此类推
		...


**API: addServerAddress(String key, AddressBean[] value)**

用于添加一个类型的服务器地址。一个类型下对应一个生产地址（Release）和若干个测试环境（Debug）地址

String key：服务器类型标识

AddressBean[] value：服务器地址的数组合集。注意：**数组中，第一个地址一定是Relase地址。其他均为Debug地址。**

	AddressBean-->

	addressKey:		地址标识

	addressHost:	服务器地址

	addressPort:	端口号

	addressDescription:		描述，会展示到弹窗的Spinner显示中。

## 2.在启动的Activity中弹出服务器选择弹框（可选，建议使用）： ##

	ChooseServerEnvDialog.showDialog(MainActivity.this, new ChooseServerEnvDialog.chooseResult() {
	    @Override
	    public void onConfirm() {
	       	//code..弹框选择完成，写你接下来的逻辑
	    }
	});


<img src="https://github.com/changshuai7/ServerAddressHelper/blob/master/screenshots/Screenshot_1.jpg"  height="640" width="350">

服务器地址带有记忆功能，第二次进入会默认选中上次的地址

弹框支持横屏

只有debug地址超过2个（含）才会进入弹框中让用户选择。如只有一个release一个debug地址，则弹框中不展示（没有必要展示）。

请务必保证弹框在程序初始位置执行，弹框弹起之前没有地址调用，避免bug。

弹框的颜色跟随主题颜色变化而变化。

如果不使用此服务器选择的弹框，也可以根据需要自行封装（不建议）


## 3.使用服务器地址 ##


	/**
     * 自动模式去获取服务器完整地址（通过dialog选择环境后，环境可实现自动识别）
     *
     * @param serverKey   服务器类型标识key
     * @param serverField 服务器地址字段(必须以 / 开头)
     * @return
     */
	 getAutoCompleteServerAddress(String serverKey, String serverField);
	 比如：ServerHelper.getAutoCompleteServerAddress("KEY_1", "/rrc/sale/demo")；

    /**
     * 获取完整服务器地址(高级)
     *
     * @param serverKey   服务器类型标识key
     * @param addressKey  key类型下地址类型key
     * @param serverField 服务器地址字段(必须以 / 开头)
     * @return
     */
     getCompleteServerAddress(String serverKey, String addressKey, String serverField);
	 比如： getCompleteServerAddress("KEY_1", "ADDRESS_KEY_2", "/rrc/sale/demo");

# 注意： #
1.在添加地址时候，AddressBean[]第一个地址一定是线上地址

2.只有在debug模式下才会弹出选择服务器环境（只可选择debug地址，线上地址不可选择），release模式下直接是走线上地址

3.一般不需要给线上地址设置端口号（传入null或者空字符串即可）。端口号默认是80，如手动设置了，则会采取设置的端口号。


没有了复杂的地址管理，是不是变得非常的简单？快来使用吧！