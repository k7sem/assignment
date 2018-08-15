CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `userpwd` varchar(45) DEFAULT NULL,
  `teamnum` int(11) DEFAULT NULL,
  `isleader` tinyint(1) DEFAULT '0',
  `employnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`,`username`) /*!80000 INVISIBLE */
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `coupon` (
  `id` int(11) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `teamid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`) /*!80000 INVISIBLE */,
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






