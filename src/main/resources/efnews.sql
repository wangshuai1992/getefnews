DROP TABLE IF EXISTS `request_log`;
CREATE TABLE `request_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `url` varchar(64) DEFAULT '' COMMENT '请求URL',
  `http_method` varchar(64) DEFAULT '' COMMENT 'http方法（GET POST 等）',
  `ip` varchar(64) DEFAULT '' COMMENT '来源IP',
  `area` varchar(256) DEFAULT '' COMMENT '来源地区',
  `class_method` varchar(64) DEFAULT '' COMMENT '访问方法',
  `args` varchar(256) DEFAULT '' COMMENT '访问参数',
  `response` BLOB DEFAULT '' COMMENT '访问返回',
  `spend_time` varchar(64) DEFAULT '' COMMENT '访问花费时间',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(256) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='请求日志表';

