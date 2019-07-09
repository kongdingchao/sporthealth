include "common.thrift"

namespace java com.sporthealth.common.thrift

enum SubType {
	INIT = 0,//初始订阅
	ADD = 1,//新增订阅
	DEL = 2,//删除订阅
	CANCEL = 3, //取消订阅
}

/*
订阅信息：id-订阅id symbol-订阅ric，feilds-订阅字段
*/
struct Subscription {
	1: SubType subType,
	2: string symbol,
	3: list<string> feilds,
}
/*
订阅者：appName-必须唯一
*/
struct Subscriber {
	1: string appName,
	2: list<Subscription> subscriptions,
}
/*
请求信息
*/
struct ReqInfo {
	1: string id,
	2: Subscriber subscriber,
}


/*
订阅服务
*/
service SubscriptionService {
	void ping(),
	common.BaseResult subscribe(1:ReqInfo reqInfo)
}