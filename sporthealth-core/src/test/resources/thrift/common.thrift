namespace java com.sporthealth.common.thrift

/*
服务请求返回
*/
struct BaseResult {
	1: string id,
	2: i32 status,
	3: string comment,
}
