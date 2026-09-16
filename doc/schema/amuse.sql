-- amuse 大型游乐设施安全监察与隐患整改管理 -- schema (liu-013)
-- 列名与基线实体契约（@TableName/@TableField）逐列对齐，改列必须同步实体。
-- 库：liu_013

CREATE TABLE IF NOT EXISTS t_am_check_item (
  id bigint NOT NULL COMMENT '主键',
  batch_no varchar(64) DEFAULT NULL COMMENT '批次号',
  row_no int DEFAULT NULL COMMENT '原始行号',
  item_code varchar(64) DEFAULT NULL COMMENT '检验项目编码',
  qty decimal(12,2) DEFAULT NULL COMMENT '抽检台次',
  status int DEFAULT NULL COMMENT '行状态 0待处理 1成功 2失败',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定期检验记录明细';

CREATE TABLE IF NOT EXISTS t_am_device (
  id bigint NOT NULL COMMENT '主键',
  device_code varchar(32) DEFAULT NULL COMMENT '设施编号',
  device_name varchar(64) DEFAULT NULL COMMENT '设施名称',
  device_type varchar(32) DEFAULT NULL COMMENT '设施类别 过山车/摩天轮/大摆锤/飞行塔',
  park_area varchar(64) DEFAULT NULL COMMENT '所在园区',
  rated_cap int DEFAULT NULL COMMENT '额定载客(人)',
  accept_date datetime DEFAULT NULL COMMENT '投用验收日期',
  status int DEFAULT NULL COMMENT '档案状态 0在用 1已停用',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='游乐设施档案';

CREATE TABLE IF NOT EXISTS t_am_due_task (
  id bigint NOT NULL COMMENT '主键',
  item_no varchar(64) DEFAULT NULL COMMENT '条目编号',
  due_at datetime DEFAULT NULL COMMENT '到期时刻',
  amount decimal(12,2) DEFAULT NULL COMMENT '巡检里程/计量值',
  status int DEFAULT NULL COMMENT '状态 0待处理 1已处理 2失败',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='到期巡检条目';

CREATE TABLE IF NOT EXISTS t_am_flow (
  id bigint NOT NULL COMMENT '主键',
  biz_no varchar(64) DEFAULT NULL COMMENT '整改单号',
  device_code varchar(32) DEFAULT NULL COMMENT '设施编号',
  hazard_desc varchar(255) DEFAULT NULL COMMENT '隐患描述',
  hazard_level int DEFAULT NULL COMMENT '隐患等级 1一般 2重大',
  stage int DEFAULT NULL COMMENT '当前环节 0..3',
  status int DEFAULT NULL COMMENT '流程状态 0待发起 1在办 2已办结',
  content varchar(255) DEFAULT NULL COMMENT '处置说明',
  last_action varchar(64) DEFAULT NULL COMMENT '最近一次流转动作',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='隐患整改单';

CREATE TABLE IF NOT EXISTS t_am_rule (
  id bigint NOT NULL COMMENT '主键',
  rule_code varchar(32) DEFAULT NULL COMMENT '判据编号',
  rule_name varchar(64) DEFAULT NULL COMMENT '判据名称',
  th1_max decimal(8,2) DEFAULT NULL COMMENT '轻微档上限(磨损量%)',
  th2_max decimal(8,2) DEFAULT NULL COMMENT '关注档上限(磨损量%)',
  th3_max decimal(8,2) DEFAULT NULL COMMENT '判废档上限(磨损量%)',
  eff_start datetime DEFAULT NULL COMMENT '生效起始时刻',
  eff_end datetime DEFAULT NULL COMMENT '生效截止时刻(不含)',
  priority int DEFAULT NULL COMMENT '优先级(数值越大越优先)',
  status int DEFAULT NULL COMMENT '判据状态 0启用 1停用',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='检验判废判据';

CREATE TABLE IF NOT EXISTS t_am_sign_bill (
  id bigint NOT NULL COMMENT '主键',
  bill_no varchar(64) DEFAULT NULL COMMENT '方案编号',
  node_no int DEFAULT NULL COMMENT '当前关口 0..2',
  sign_mode int DEFAULT NULL COMMENT '会签方式 0或签 1会签',
  need_count int DEFAULT NULL COMMENT '本关口应签人数',
  sign_count int DEFAULT NULL COMMENT '本关口已签票数',
  status int DEFAULT NULL COMMENT '单据结果 0在办 1已定稿 2已否决',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='重大隐患整改方案';

CREATE TABLE IF NOT EXISTS t_am_warn (
  id bigint NOT NULL COMMENT '主键',
  warn_no varchar(64) DEFAULT NULL COMMENT '预警单号',
  device_id bigint DEFAULT NULL COMMENT '设施ID',
  device_code varchar(32) DEFAULT NULL COMMENT '设施编号(冗余，以档案为准)',
  warn_level int DEFAULT NULL COMMENT '预警级别 1蓝色 2黄色 3橙色 4红色',
  raise_date datetime DEFAULT NULL COMMENT '预警日期',
  due_date datetime DEFAULT NULL COMMENT '处置期限',
  remain_days int DEFAULT NULL COMMENT '剩余处置天数',
  warn_by varchar(128) DEFAULT NULL COMMENT '填报人',
  warn_status int DEFAULT NULL COMMENT '处置进度 0待处置 1已处置 2已关闭',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='隐患预警单';

-- 初始档案数据（验收测试依赖 id=1 启用 / id=2 停用）
INSERT INTO t_am_device (id, device_code, device_name, device_type, park_area, rated_cap, accept_date, status, del_flag, create_by, create_time)
VALUES (1, 'AM-0001', '星际飞车过山车', '过山车', '欢乐世界园区', 24, DATE_SUB(NOW(), INTERVAL 1500 DAY), 0, 0, 'sys', NOW()),
       (2, 'AM-0002', '停用的老摩天轮', '摩天轮', '南湖公园', 96, DATE_SUB(NOW(), INTERVAL 5200 DAY), 1, 0, 'sys', NOW());
