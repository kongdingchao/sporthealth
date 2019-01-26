<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html >
<head>
    <title>sporthealth-首页</title>
    <%--<span>实现ajax请求:搜索框</span>--%>
    <style type="text/css">
        #mydiv {
            position: absolute;
            left: 50%;
            top: 50%;
            margin-left: -200px;
            margin-top: -50px;
        }
        .mouseOver {
            background: #708090;
            color: #FFFAFA;
        }
        .mouseOut {
            background: #FFFAFA;
            color: #000000;
        }
    </style>
    <script type="text/javascript">
        var xmlHttp;
        //获取用户输入内容的关联信息的函数
        function getMoreContents() {
            //首先获取用户的输入
            var content = document.getElementById("keyword");
            if (content.value == "") {
                clearContent();
                return;
            }
            //然后给服务器发送用户输入的内容,因为采用ajax异步发送数据,
            //所以使用XmlHttp对象
            xmlHttp = creatXMLHttp();
            //给服务器发送数据
            var url = "searchUser?keyword=" + escape(content.value);
            xmlHttp.open("GET", url, true);
            //xmlHttp绑定回调方法，这个回调方法会在xmlHttp状态改变的时候被调用
            //xmlHttp 状态0-4,我们只关心4(complete)，完成后再调用回调方法才有意义
            xmlHttp.onreadystatechange = callback;
            xmlHttp.send(null);
        }
        //获取XmlHttp对象
        function creatXMLHttp() {
            //对于大多数浏览器都适用
            var xmlHttp;
            if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();
            }
            //要考虑浏览器的兼容性
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                if (!xmlHttp) {
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                }
            }
            return xmlHttp;
        }
        //回调函数
        function callback() {
            //4代表成功
            if (xmlHttp.readyState == 4) {
                //200代表服务器响应成功
                if (xmlHttp.status == 200) {
                    //交互成功 获得响应的数据 是文本格式
                    var result = xmlHttp.responseText;
                    console.log(result);
                    //解析数据
                    var json = eval("(" + result + ")");
                    //alert(json);
                    //获取数据后动态显示 展示输入框下面
                    setContent(json);
                }
            }
        }
        //设置关联数据展示
        function setContent(contents) {
            clearContent();
            setLocation();
            //获取关联数据的长度,以此来确定生成的<tr>
            var size = contents.length;
            //设置内容
            for (var i = 0; i < size; i++) {
                var nextNode = contents[i].name;//代表的是JSon数据的第i个元素
                var tr = document.createElement("tr");
                var td = document.createElement("td");
                td.setAttribute("border", "0");
                td.setAttribute("bgcolor", "#FFFAFA");
                td.onmouseover = function () {
                    this.className = 'mouseOver';
                };
                td.onmouseout = function () {
                    this.className = 'mouseOut';
                };
                td.onmousedown=function(){
                    //当鼠标点击一个关联数据的时候,被选中的数据 自动填充到输入框里面
                    document.getElementById("keyword").value=this.innerHTML;
                    //清除div边框
                    document.getElementById("popDiv").style.border="none";
                };
                var text = document.createTextNode(nextNode);
                td.appendChild(text);
                tr.appendChild(td);
                document.getElementById("content_table_body").appendChild(tr);
            }
        }
        //清空之前的数据
        function clearContent() {
            var contentTableBody = document.getElementById("content_table_body");
            var size = contentTableBody.childNodes.length;
            for (var i = size - 1; i >= 0; i--) {
                contentTableBody.removeChild(contentTableBody.childNodes[i]);
            }
            document.getElementById("popdiv").style.border = "none";
        }
        //输入框失去焦点 清空
        function keywordBlur() {
            clearContent();
        }
        //设置显示关联信息
        function setLocation() {
            //关联信息的显示位置
            var content = document.getElementById("keyword");
            var width = content.offsetWidth;//输入框的宽度
            var left = content["offsetLeft"];//距离左边框的距离
            var top = content["offsetTop"] + content.offsetHeight;//距离顶部
            //获取显示数据div
            var popdiv = document.getElementById("popdiv");
            popdiv.style.border = "black 1px solid";
            popdiv.style.left = left + "px";
            popdiv.style.top = top + "px";
            popdiv.style.width = width + "px";
            document.getElementById("content_table").style.width = width + "px";
        }
    </script>
</head>
<body>
<div id="mydiv">
    <form action="userInfos" method="get">
        <!--输入框-->
        <input name="keyword" type="text" size="50" id="keyword" onkeyup="getMoreContents()"
               onblur="keywordBlur()" onfocus="getMoreContents()"/>
        <input type="submit" value="查询用户详细信息" width="50px">
        <!--内容展示区域-->
        <div id="popdiv">
            <table id="content_table" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0">
                <tbody id="content_table_body">
                <%--动态查询出来的数据,显示在此--%>

                </tbody>
            </table>

        </div>
    </form>
</div>

</body>
</html>
