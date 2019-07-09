namespace java com.sporthealth.common.thrift

/*
即期牌价：
*/
struct SpotRate {
	1: string symbol,
	2: double bid,
	3: double offer,
	4: double mid,
	5: double quoteunit,
}
