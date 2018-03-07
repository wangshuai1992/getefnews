var lan_config = {
    "zh_cn": {
        "title-1": "无尽的边界 活动预告（contact me by : wang409322824@gmail.com。有时更新代码会重启服务器，如果偶尔访问不了可以稍后再试）",
        "title-2": "tips: 【由于官方更新做了限制，目前无法再查看活动预告了！】",
        "thead-1": "时间",
        "thead-2": "活动图片",
        "thead-3": "活动详情",
        "server_busy": "服务器繁忙，请稍后再试",
        "no_detail_now": "暂无详情"
    },
    "en_us": {
        "title-1": "EndlessFrontier upcoming event! (contact me by : wang409322824@gmail.com. Service may be unavailable during server-restart sometimes, please try later)",
        "title-2": "tips: 【can`t get upcoming from ekkorr`s server any more due to their latest update, sry】",
        "thead-1": "event-date",
        "thead-2": "event-image",
        "thead-3": "event-detail",
        "server_busy": "server is busy, please try later",
        "no_detail_now": "no detail now"
    }
}

var lanType = localStorage.lanType;

if (!lanType) {
    lanType = "zh_cn";
}

var lanVal = lan_config[lanType];

function loadHtmlLanguage() {
    if (!lanType) {
        lanVal = lan_config['zh_cn'];
    } else {
        lanVal = lan_config[lanType];
    }

    for (var id in lanVal) {
        var obj = $("#" + id);
        if (obj.length == 0) {
            continue;
        }

        obj.html(lanVal[id]);
    }

}