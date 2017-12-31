<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>

    <title>NEWS</title>

    <meta content="no-cache" http-equiv="cache-control">
    <meta content="no-cache" http-equiv="pragma">

    <style>
        * {
            margin: 0em;
            padding: 0em;
        }

        body {
            background-color: #BDBDC2;
            font-family: Dotum, Helvetica, sans-serif;
        }

        img {
            max-width: 100%;
            height: auto;
        }

        ol, ul {
            list-style: outside none none;
        }

        a {
            text-decoration: none;
        }

        .wrap {
            font-size: 1.5em;
            background-color: #FFFFFF;
        }

        .wrap #container {
            margin-bottom: 2em;
        }

        .bottom {
            background-color: #A4A4AC;
            /* padding: 1em; */
        }

        .bottom a {
            color: #000000;
            font-weight: bold;
        }

        .bottom #list {
            float: left;
            padding: 1em;
        }

        .bottom #top {
            float: right;
            padding: 1em;
        }

        .bottom:after {
            content: '';
            display: block;
            clear: both;
        }

        /* news-text */
        .news-text {
            color: #000000;
            background-color: #EBEBED;
            border-bottom: 1px solid #A4A4AC;
        }

        .news-text #area {
            padding: 1em;
        }

        .news-text a {
            color: #000000;
        }

        .news-text #news-title {
            display: inline-block;
            font-weight: bold;
        }

        .news-text #news-date {
            display: inline-block;
            float: right;
        }

        .news-text:after {
            content: '';
            display: block;
            clear: both;
        }

        /* news-img */
        .news-img {
            padding: 1em;
            border-bottom: 1px solid #A4A4AC;
        }

        /* news-view */
        .news-view {
            padding: 1em;
            color: #000000;
            background-color: #EBEBED;
        }

        .news-view a {
            color: #336699;
            font-weight: bold;
        }

        .news-view #news-title {
            display: inline-block;
        }

        .news-view #news-date {
            display: inline-block;
            float: right;
        }

        .news-view:after {
            content: '';
            display: block;
            clear: both;
        }

        .news-content {
            color: #333333;
        }

        /* news-content */
        .news-content {
            padding: 1em;
        }

        .news-content #news-img {
            padding-bottom: 1em;
        }

        a {
            display: inline-block;
            position: relative;
            padding-left: 6px;
            /* Font styles */
            text-decoration: none;
            color: #6AB3EC;
            text-shadow: 0 1px 1px rgba(255, 255, 255, 0.9);
        }

        a:hover {
            color: #3C9CE7;
        }

        a:before {
            content: "\25BA";
            font-size: 80%;
            display: inline-block;
            padding-right: 3px;
            pointer-events: none;
        }

        a:hover:before {
            color: #F2BF97;
        }

        body {
            width: 80%;
            margin: 40px auto;
            font-family: 'trebuchet MS', 'Lucida sans', Arial;
            font-size: 14px;
            color: #444;
        }

        table {
            *border-collapse: collapse; /* IE7 and lower */
            border-spacing: 0;
            width: 100%;
        }

        .bordered {
            border: solid #ccc 1px;
            -moz-border-radius: 6px;
            -webkit-border-radius: 6px;
            border-radius: 6px;
            -webkit-box-shadow: 0 1px 1px #ccc;
            -moz-box-shadow: 0 1px 1px #ccc;
            box-shadow: 0 1px 1px #ccc;
        }

        .bordered tr:hover {
            background: #fbf8e9;
            -o-transition: all 0.1s ease-in-out;
            -webkit-transition: all 0.1s ease-in-out;
            -moz-transition: all 0.1s ease-in-out;
            -ms-transition: all 0.1s ease-in-out;
            transition: all 0.1s ease-in-out;
        }

        .bordered td, .bordered th {
            border-left: 1px solid #ccc;
            border-top: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }

        .bordered th {
            background-color: #dce9f9;
            background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc), to(#dce9f9));
            background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
            background-image: -moz-linear-gradient(top, #ebf3fc, #dce9f9);
            background-image: -ms-linear-gradient(top, #ebf3fc, #dce9f9);
            background-image: -o-linear-gradient(top, #ebf3fc, #dce9f9);
            background-image: linear-gradient(top, #ebf3fc, #dce9f9);
            -webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
            -moz-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
            box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
            border-top: none;
            text-shadow: 0 1px 0 rgba(255, 255, 255, .5);
        }

        .bordered td:first-child, .bordered th:first-child {
            border-left: none;
        }

        .bordered th:first-child {
            -moz-border-radius: 6px 0 0 0;
            -webkit-border-radius: 6px 0 0 0;
            border-radius: 6px 0 0 0;
        }

        .bordered th:last-child {
            -moz-border-radius: 0 6px 0 0;
            -webkit-border-radius: 0 6px 0 0;
            border-radius: 0 6px 0 0;
        }

        .bordered th:only-child {
            -moz-border-radius: 6px 6px 0 0;
            -webkit-border-radius: 6px 6px 0 0;
            border-radius: 6px 6px 0 0;
        }

        .bordered tr:last-child td:first-child {
            -moz-border-radius: 0 0 0 6px;
            -webkit-border-radius: 0 0 0 6px;
            border-radius: 0 0 0 6px;
        }

        .bordered tr:last-child td:last-child {
            -moz-border-radius: 0 0 6px 0;
            -webkit-border-radius: 0 0 6px 0;
            border-radius: 0 0 6px 0;
        }

        /*----------------------*/

        .zebra td, .zebra th {
            padding: 10px;
            border-bottom: 1px solid #f2f2f2;
        }

        .zebra tbody tr:nth-child(even) {
            background: #f5f5f5;
            -webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
            -moz-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
            box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
        }

        .zebra th {
            text-align: left;
            text-shadow: 0 1px 0 rgba(255, 255, 255, .5);
            border-bottom: 1px solid #ccc;
            background-color: #eee;
            background-image: -webkit-gradient(linear, left top, left bottom, from(#f5f5f5), to(#eee));
            background-image: -webkit-linear-gradient(top, #f5f5f5, #eee);
            background-image: -moz-linear-gradient(top, #f5f5f5, #eee);
            background-image: -ms-linear-gradient(top, #f5f5f5, #eee);
            background-image: -o-linear-gradient(top, #f5f5f5, #eee);
            background-image: linear-gradient(top, #f5f5f5, #eee);
        }

        .zebra th:first-child {
            -moz-border-radius: 6px 0 0 0;
            -webkit-border-radius: 6px 0 0 0;
            border-radius: 6px 0 0 0;
        }

        .zebra th:last-child {
            -moz-border-radius: 0 6px 0 0;
            -webkit-border-radius: 0 6px 0 0;
            border-radius: 0 6px 0 0;
        }

        .zebra th:only-child {
            -moz-border-radius: 6px 6px 0 0;
            -webkit-border-radius: 6px 6px 0 0;
            border-radius: 6px 6px 0 0;
        }

        .zebra tfoot td {
            border-bottom: 0;
            border-top: 1px solid #fff;
            background-color: #f1f1f1;
        }

        .zebra tfoot td:first-child {
            -moz-border-radius: 0 0 0 6px;
            -webkit-border-radius: 0 0 0 6px;
            border-radius: 0 0 0 6px;
        }

        .zebra tfoot td:last-child {
            -moz-border-radius: 0 0 6px 0;
            -webkit-border-radius: 0 0 6px 0;
            border-radius: 0 0 6px 0;
        }

        .zebra tfoot td:only-child {
            -moz-border-radius: 0 0 6px 6px;
            -webkit-border-radius: 0 0 6px 6px
            border-radius: 0 0 6px 6px
        }


    </style>
    <script src="/js/jquery-3.2.1.js" type="text/javascript"></script>
</head>

<body>

<h2>无尽的边界 活动预告（欢迎提BUG~ 有时更新代码会重启服务器，如果偶尔访问不了可以稍后再试）</h2>
<div class="wrap">
    <table id="container-table" class="bordered">
        <thead>
        <td>时间</td>
        <td>活动图片</td>
        <td>活动详情</td>
        </thead>
        <tbody id="container-tbody">
        <#-- <tr>
            <td>20171231</td>
            <td>
                <img src="http://ef-image.s3-website-us-west-1.amazonaws.com/news_20171231_02.jpg">
            </td>
            <td>
                <a href="getNewsDetailMultiLang?seq=7473&domain=A&lang=EN">
                    文案
                </a>
            </td>
        </tr> -->
        </tbody>
    </table>

    <!-- bottom : top -->
    <div class="bottom">
        <a href="#">
            <div id="top">▲ TOP</div>
        </a>
    </div>

</div><!-- //wrap -->

</body>

<script type="text/javascript">
    var datas;

    $(function () {
        $.ajax({
            url: "/getTableData",
            async: false,
            type: "POST",
            success: function (result) {
                if (!result) {
                    alert("服务器繁忙，请稍后再试");
                } else {
                    datas = result;
                    buildTable();
                }
            }
        });
    });

    Date.prototype.format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    function buildTable() {
        $("#container-tbody").empty();

        for (var i in datas) {
            var data = datas[i];

            var dateStr = getDate(data.date).format("yyyy-MM-dd");
            // var dateStr = data.date;
            var imgUrl = data.imgUrl;
            var title = data.title;
            var detailUrl = data.detailUrl;

            var tr = $("<tr></tr>");
            var dateTd = $("<td>" + dateStr + "</td>");
            var imgTd = $("<td><img src='" + imgUrl + "'></td>");
            var detailTd = $("<td><a href='" + detailUrl + "' target='new_window'>" + title + "</a></td>");

            if (!title) {
                detailTd = $("<td>无</td>");
            }

            tr.append(dateTd).append(imgTd).append(detailTd);
            $("#container-tbody").prepend(tr);
        }
    }

    //把"yyyyMMdd"格式转为日期对象
    function getDate(dateStr) {
        var year = dateStr.substring(0, 4);
        var month = dateStr.substring(4, 6);
        var day = dateStr.substring(6, 8);

        return new Date(year, month-1, day);
    }

</script>

</html>
