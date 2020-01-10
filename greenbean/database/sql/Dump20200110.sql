CREATE DATABASE  IF NOT EXISTS `greenbean` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `greenbean`;
-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: greenbean
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_author`
--

DROP TABLE IF EXISTS `t_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作者';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_author`
--

LOCK TABLES `t_author` WRITE;
/*!40000 ALTER TABLE `t_author` DISABLE KEYS */;
INSERT INTO `t_author` VALUES (1,'阿尔贝·加缪'),(2,'冀可平'),(3,'卡佩勒'),(4,'莫南'),(5,'菲利普·津巴多'),(6,'理查德·格里格'),(7,'阿加莎・克里斯蒂'),(10,'[美] 丹尼尔·凯斯'),(11,'李银河'),(12,'[日] 东野圭吾'),(13,'[美] Adrian Banner'),(14,'周志明'),(15,'（美）Randal E.Bryant'),(16,'David O\'Hallaron');
/*!40000 ALTER TABLE `t_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_authority`
--

DROP TABLE IF EXISTS `t_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_authority` (
  `user_id` int(11) NOT NULL COMMENT '用户ID，参照t_user表ID字段',
  `authority` varchar(45) NOT NULL COMMENT '权限',
  PRIMARY KEY (`user_id`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_authority`
--

LOCK TABLES `t_authority` WRITE;
/*!40000 ALTER TABLE `t_authority` DISABLE KEYS */;
INSERT INTO `t_authority` VALUES (1,'ADMIN'),(2,'USER'),(4,'USER'),(6,'USER'),(7,'USER'),(8,'USER'),(9,'USER');
/*!40000 ALTER TABLE `t_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_book`
--

DROP TABLE IF EXISTS `t_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '书名',
  `isbn` varchar(45) DEFAULT NULL,
  `picture` varchar(45) DEFAULT NULL,
  `publisher` varchar(45) DEFAULT NULL COMMENT '出版社',
  `publication_year` int(4) DEFAULT NULL COMMENT '出版日期',
  `price` float DEFAULT NULL COMMENT '价格',
  `publication_month` int(2) DEFAULT NULL,
  `publication_day` int(2) DEFAULT NULL,
  `subtitle` varchar(100) DEFAULT NULL,
  `original_name` varchar(100) DEFAULT NULL,
  `binding` int(1) DEFAULT NULL,
  `page` int(4) DEFAULT NULL,
  `content_intro` varchar(3000) DEFAULT NULL,
  `author_intro` varchar(3000) DEFAULT NULL,
  `directory` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='书';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_book`
--

LOCK TABLES `t_book` WRITE;
/*!40000 ALTER TABLE `t_book` DISABLE KEYS */;
INSERT INTO `t_book` VALUES (1,'心理学与生活','9787115111302','s1068520.jpg','人民邮电出版社',2003,88,10,NULL,NULL,NULL,1,621,'《心理学与生活》是美国斯坦福大学多年来使用的教材，也是在美国许多大学里推广使用的经典教材，被ETS推荐为GRE心理学专项考试的主要参考用书，还是被许多国家大学的“普通心理学”课程选用的教材。这本教科书写作流畅，通俗易懂，深入生活，把心理学理论与知识联系人们的日常生活与工作，使它同样也成为一般大众了解心理学与自己的极好读物。\n\n作为一本包含着丰富的教育思想和独特教学方法的成熟教材，原书中所有元素——如由600余条词汇及解释组成的“专业术语表”，2000余条“参考文献”，以及近1000条的“人名和主题索引”等等，对于教学、研究和学习都十分宝贵，此中译本完整地翻译和保留了这些资料。','理查德·格里格（Richard J. Gerrig）是美国纽约州立大学的心理学教授。获Lex Hixon社会科学领域杰出教师奖。在认知心理学研究领域有专长，是美国心理学会实验心理学分会的会员。从《心理学与生活》这部经典教科书第14版修订时开始，格里格成为该书的合著者。\n\n菲利普·津巴多（Philip G. Zimbardo）是美国斯坦福大学的心理学教授，当代著名心理学家，美国心理学会主席。40多年来，由于他在心理学研究和教学领域的杰出贡献，美国心理学会特向津巴多频发了Hilgard普通心理学终生成就奖。由他开创的《心理学与生活》这部经典教科书哺育了一代又一代心理学工作者。津巴多主动让贤，推举格里格为《心理学与生活》第16版的第一作者。','序言\n第一章 生活中的心理学\n第二章 心理学的研究方法\n第三章 行为的生物学基础\n第四章 感觉\n第五章 知觉\n第六章 心理，意识和其他状态\n第七章 学习与行为分析\n第八章 记忆\n第九章 认知过程\n第十章 智力与智力测验\n第十一章 人的毕生发展\n第十二章 动机\n第十三章 情绪、压力和健康\n第十四章 理解人类人格\n第十五章 心理障碍\n第十六章 心理治疗\n第十七章 社会过程与关系\n第十八章 社会心理学，社会和文化'),(2,'你好!法语-1-学生用书-A1','9787513524223','s24408084.jpg','外语教学与研究出版社',2012,49.9,9,1,NULL,NULL,1,237,'你好！法语1：学生用书，ISBN：9787513524223，作者：王海燕 主编，冀可平，（法）卡佩勒，（法）莫南 编著',NULL,NULL),(3,'局外人','9787532751471','s4468484.jpg','上海译文出版社',2010,22,8,NULL,NULL,'L\'Etranger',2,128,'《局外人》是加缪小说的成名作和代表作之一，堪称20世纪整个西方文坛最具有划时代意义最著名小说之一，“局外人”也由此成为整个西方文学-哲学中最经典的人物形象和最重要的关键词之一。','阿尔贝·加缪（Albert Camus，1913—1960），法国声名卓著的小说家、散文家和剧作家，“存在主义”文学的大师。1957年因“热情而冷静地阐明了当代向人类良知提出的种种问题”而获诺贝尔文学奖，是有史以来最年轻的诺奖获奖作家之一。加缪在他的小说、戏剧、随笔和论著中深刻地揭示出人在异己的世界中的孤独、个人与自身的日益异化，以及罪恶和死亡的不可避免，但他在揭示出世界的荒诞的同时却并不绝望和颓丧，他主张要在荒诞中奋起反抗，在绝望中坚持真理和正义，他为世人指出了一条基督教和马克思主义以外的自由人道主义道路。他直面惨淡人生的勇气，他“知其不可而为之”的大无畏精神使他在第二次世界大战之后不仅在法国，而且在欧洲并最终在全世界成为他那一代人的代言人和下一代人的精神导师。',NULL),(4,'路西法效应','9787108033109','s6793769.jpg','生活·读书·新知三联书店',2010,48,3,NULL,'好人是如何变成恶魔的','The Lucifer Effect: Understanding How Good People Turn Evil',1,544,'一批彼此并不相识的年轻人——身心健康、情绪稳定的大学生，走进了“斯坦福监狱”。他们并没有犯罪，只是受募到“监狱”——斯坦福大学的一个精心布置的地下室，接受一项试验。大学生们被随机地分为“狱卒”和“犯人”，然后开始了为期两周的试验。然而，试验仅仅进行了一周，原本单纯的大学生，已变成了残暴的狱卒和崩溃的犯人，试验不得不终止了。受试者强烈感受到角色规范的影响，使作者得以深度剖析复杂的人性，透析“情境力量”对个人行为的影响，并分析造成监狱虐囚和种族屠杀的情境影响。日常生活中的种种社会角色剧本的规范与约束，会不会使我们像上帝最钟爱的天使路西法那样不知不觉中对他人做出难以置信的事，从而堕落成为魔鬼？我们如何抗拒情境影响力？作为心理学教授的作者也对此进行了研究，并做出了极有说服力的分析。','菲利普·津巴多（Philip Zimbardo，1933-）毕业于耶鲁大学，曾先后执教于耶鲁大学、纽约大学、哥伦比亚大学和斯坦福大学，现为斯坦福大学心理学系荣退教授。他的《害羞》（Shyness）、《心理学与生活》（Psychology and Life，与 Richard Gerrig合著）两书总销量已逾250万本。津巴多曾任美国心理学会主席，现任斯坦福大学恐怖主义跨领域政策、教育与研究中心主任。他编创了美国公共电视台的获奖节目《探索心理学》（Discovering Psychology），并在片中担任主持人。2004年，他应邀担任伊拉克阿布格莱布监狱美军虐囚案的专家证人。由于津巴多教授四十多年来在心理学研究和教学领域的杰出贡献，美国心理学会特向他颁发了希尔加德（Ernest R.Hilgard）普通心理学终身成就奖。','第1章　邪恶心理学：情境中的性格转换'),(25,'24个比利','9787513559263',NULL,'湖岸出版/外语教学与研究出版社',2015,39,NULL,NULL,'','The Minds of Billy Milligan',1,448,'1977年，美国俄亥俄州连续强暴案嫌犯比利‧米利根被警方逮捕，但是他对自己犯下的罪行居然毫无记忆。事实上，在他体内总共有24个人格存在，这些人格不仅在性格上，甚至连智商、年龄、国籍、语言、性别等方面也都不尽相同。这些不可思议的人格，到底是如何产生的呢？他到底是个欺骗公众的骗子，或只是个不幸的受害者？\r\n\r\n父亲自杀，继父百般虐待，这让比利一方面迫切地渴望逃避这个世界──多次自杀，另一方面求生的本能又来安慰、保护自己，这两种力量纽结在一起，将比利撕成碎片……\r\n\r\n当比利闭上眼睛，会有守护者里根出来击退施虐者，会有8岁的承受者戴维哭泣，女同性恋阿达拉娜、流氓菲利普、职业骗子凯文、小丑利伊、工作狂马克……像是一个队伍，每个人承担不同任务\r\n\r\n一个人格来承受我的痛苦\r\n\r\n一个人格来表现我的快乐\r\n\r\n一个人格来保护我的身体\r\n\r\n一个人格来享受他人的关爱\r\n\r\n一个人格来学习逃脱\r\n\r\n……\r\n\r\n【推荐】\r\n\r\n◎多重人格分裂纪实小说！心理纪实的巅峰！\r\n\r\n◎莱昂纳多•迪卡普里奥为之等待20年的“故事”，改编电影《拥挤的房间》(The Crowded Room)正在制作。启发了《搏击俱乐部》《致命ID》《禁闭岛》等烧脑电影！\r\n\r\n◎比利是世界上第一位在四名精神病医生和一名心理学家共同见证下接受彻底检查的多重人格症患者！\r\n\r\n◎小S×阿信×吴奇隆×赵又廷×王心凌×陈乔恩×郑秀文×范玮琪×彭浩翔×钮承泽……港台众明星都在追看的一本心理纪实小说。潘玮柏更发行同名专辑！\r\n\r\n◎日本五年狂销六百万册、中国台湾累计销售过百万册。\r\n\r\n◎当悲伤太多的时候，一个人已经无法承受，我就把投注在一个人身上的所有煎熬分别来接受。\r\n\r\n◎《献给阿尔吉侬的花束》作者最负盛名作品。','关于作者\r\n\r\n丹尼尔•凯斯Daniel Keyes，1927-2014年\r\n\r\n1927年生于纽约。他曾在杂志社工作（即后来的漫威漫画），在转行任中学老师时，开始从事小说创作。因《献给阿尔吉侬的花束》荣获“雨果奖”和“星云奖”。\r\n\r\n拥有心理学背景的丹尼尔•凯斯，擅长在小说中探讨人类最精微、最深层的心理问题。他在俄亥俄大学任教时，便开始以多重人格为创作的方向，1981年出版的《24个比利》，让他的名字与“精神分析小说”划上等号。\r\n\r\n2014年6月15日丹尼尔•凯斯因肺炎并发症于佛罗里达州南部的家中过世。\r\n\r\n关于比利\r\n\r\n威廉•斯坦利•米利根William Stanley Milligan, 1955-2014年\r\n\r\n又被称为比利•米利根（Billy Milligan），1955年生于迈阿密。他于1977年因在俄亥俄州立大学犯下三宗强奸罪被警方逮捕。在审讯过程中，比利被诊断为罕见的多重人格分裂症患者，他亦因此被判无罪，案件受到高度关注。\r\n\r\n1981年，作家丹尼尔•凯斯创作的纪实传记《24个比利》出版，该书详细记录了比利从童年到1979年法院审判期间的经历，他复杂的精神状态、令人困惑的多个人格。1993年丹尼尔•凯斯写了续作《比利战争》，进一步披露比利于1979年被关到利玛医院直到1991年法院正式宣布假释刑期结束、比利终获自由这一段时间的经历，但第二本书因为种种原因一直无法出版。迄今只出版有繁体中文和日文译本。\r\n\r\n1996年比利曾发表一篇声明，表示他居住在加州，拥有一家小制片公司，尝试制作一部基于《24个比利》和《比利战争》的电影，他也训练过约翰尼•德普等一些演员如何饰演自己。\r\n\r\n2014年12月12日比利因癌症于俄亥俄州哥伦布去世。\r\n\r\n关于译者\r\n\r\n邢世阳，1980年生人，机械工程师，现居科隆。','致谢\r\n序言\r\n内在人格\r\n第一部 混乱时期\r\n一章 ...六章\r\n第二部 老师诞生\r\n七章 ...十九章\r\n第三部 超越疯狂\r\n二十章 ... 二十三章\r\n尾声\r\n后记\r\n作者附记'),(30,'性学入门','9787552004489',NULL,'上海社会科学院出版社',2014,26.8,5,1,'人类性学领域的探索','',1,245,'此书系李银河主编的对性学主要研究领域和代表人物的介绍性著作。全面翔实地对性学所涵盖的所有研究领域和热点问题进行详尽的梳理和介绍，其中包括性研究的历史沿革，性的个人层面、社会层面、政治层面和哲学层面。是一本性学入门书或者说是概论类图书。','编者李银河：\r\n\r\n1952年生于北京。中国社会科学院社会学研究所研究员、教授、博士生导师，美国匹兹堡大学社会学博士，北京大学社会学博士后，英国剑桥大学访问学者。主要著作有《同性恋亚文化》《中国人的性爱与婚姻》《中国女性的性与爱》等。','1·历史沿革\r\n1·1 源与流\r\n1·2 学者与著作\r\n1·2·1 埃利斯\r\n1·2·2弗洛伊德\r\n1·2·3 金西\r\n1·2·4 马斯特斯和约翰逊\r\n1·2·5 其他性学研究者\r\n1·2·6 涉猎性学领域的学者\r\n1·3 两种作用或两种评价\r\n2·个人层面\r\n2·1 性欲望\r\n2·1·1 性的生理基础\r\n2·1·2 性欲种类\r\n2·1·3 儿童性欲\r\n2·1·4 性倾向\r\n2·2 性行为\r\n2·2·1 人类性反应周期\r\n2·2·2 女性性反应\r\n2·2·3 男性性反应\r\n2·2·4 性宣泄途径\r\n2·2·5 女性性高潮\r\n2·2·6 自慰\r\n2·2·7 性频率\r\n2·3 性治疗\r\n2·3·1 治疗的理念与方法\r\n2·3·2 治疗内容\r\n2·3·3 性病治疗\r\n3·社会层面\r\n3·1 性与爱情\r\n3·2 性与婚姻\r\n3·2·1 婚姻性关系\r\n3·2·2 非婚性关系\r\n3·2·3 生育\r\n3·3 性与法律\r\n3·3·1 涉性法律\r\n3·3·2 有受害者的性犯罪\r\n3·3·3 无受害者的性犯罪\r\n3·3·4 性立法思想\r\n3·4 性与文化\r\n3·4·1 特罗布里恩德文化个案\r\n3·4·2 萨摩亚文化个案\r\n3·4·3 中国文化个案\r\n3·5 性与道德\r\n3·5·1 反性道德观\r\n3·5·2 褒性道德观\r\n4·政治层面\r\n4·1 人口与优生学问题\r\n4·1·1 人口问题\r\n4·1·2 优生学问题\r\n4·2 性别政治\r\n4·2·1 性别权力关系\r\n4·2·2 女性的性权利\r\n4·2·3 性的性别差异\r\n4·3 同性恋与身份政治\r\n4·3·1 同性恋政治\r\n4·3·2 身份政治\r\n4·4 色情业问题\r\n4·4·1 卖淫问题\r\n4·4·2 淫秽品问题\r\n4·5 性革命\r\n5·哲学层面\r\n5·1 社会建构论\r\n5·2 性压抑理论\r\n5·3 性的民主化理论\r\n5·4 酷儿理论'),(31,'白夜行','9787544258609',NULL,'南海出版公司',2013,39.5,1,1,'','',2,538,'东野圭吾万千书迷心中的无冕之王\r\n\r\n周刊文春推理小说年度BEST10第1名\r\n\r\n本格推理小说年度BEST10第2名\r\n\r\n《白夜行》是东野圭吾迄今口碑最好的长篇杰作，具备经典名著的一切要素：\r\n\r\n一宗离奇命案牵出跨度近20年步步惊心的故事：悲凉的爱情、吊诡的命运、令人发指的犯罪、复杂人性的对决与救赎……\r\n\r\n-------------------------------------------------------------------\r\n\r\n1973年，大阪的一栋废弃建筑中发现一名遭利器刺死的男子。案件扑朔迷离，悬而未决。\r\n\r\n此后20年间，案件滋生出的恶逐渐萌芽生长，绽放出恶之花。案件相关者的人生逐渐被越来越重的阴影笼罩……\r\n\r\n“我的天空里没有太阳，总是黑夜，但并不暗，因为有东西代替了太阳。虽然没有太阳那么明亮，但对我来说已经足够。\r\n\r\n凭借着这份光，我便能把黑夜当成白天。\r\n\r\n我从来就没有太阳，所以不怕失去。”\r\n\r\n“只希望能手牵手在太阳下散步”，这句象征本书故事内核的绝望念想，有如一个美丽的幌子，随着无数凌乱、压抑、悲凉的事件片段如纪录片一样一一还原，最后一丝温情也被完全抛弃，万千读者在一曲救赎罪恶的爱情之中悲切动容。','东野圭吾\r\n\r\n日本著名作家。\r\n\r\n1985年，凭《放学后》获第31届江户川乱步奖，开始专职写作；\r\n\r\n1999年，《秘密》获第52届日本推理作家协会奖；此后《白夜行》、《单恋》、《信》、《幻夜》先后入围直木奖。\r\n\r\n2005年出版的《嫌疑人X的献身》史无前例地将第134届直木奖、第6届本格推理小说大奖，以及年度三大推理小说排行榜第1名一并斩获；\r\n\r\n2008年，《流星之绊》获第43届新风奖。\r\n\r\n2009年出版的《新参者》获两大推理小说排行榜年度第1名；\r\n\r\n2012年，《浪矢杂货店的奇迹》获第7届中央公论文艺奖。',''),(32,'无人生还','9787513312929',NULL,'新星出版社',2013,28,7,NULL,'阿加莎·克里斯蒂侦探作品集10','And Then There Were None',1,237,'十个素不相识、身份各异的人受邀前往德文郡海岸边一座孤岛上的豪宅。客人到齐后，主人却没有出现。\r\n\r\n当晚，一个神秘的声音发出指控，分别说出每个人心中罪恶的秘密。接着，一位客人离奇死亡。暴风雨让小岛与世隔绝，《十个小士兵》——这首古老的童谣成了死亡咒语。如同歌谣中预言 的，客人一个接一个死去……杀人游戏结束后，竟无一人生还！','无可争议的侦探小说女王，侦探文学史上最伟大的作家之一。\r\n\r\n阿加莎•克里斯蒂原名为阿加莎•玛丽•克拉丽莎•米勒，一八九○年九月十五日生于英国德文郡托基的阿什菲尔德宅邸。她几乎没有接受过正规的教育，但酷爱阅读，尤其痴迷于歇洛克•福尔摩斯的故事。\r\n\r\n第一次世界大战期间，阿加莎•克里斯蒂成了一名志愿者。战争结束后，她创作了自己的第一部侦探小说《斯泰尔斯庄园奇案》。几经周折，作品于一九二○正式出版，由此开启了克里斯蒂辉煌的创作生涯。一九二六年，《罗杰疑案》由哈珀柯林斯出版公司出版。这部作品一举奠定了阿加莎•克里斯蒂在侦探文学领域不可撼动的地位。之后，她又陆续出版了《东方快车谋杀案》、《ABC 谋杀案》、《尼罗河上的惨案》、《无人生还》、《阳光下的罪恶》等脍炙人口的作品。时至今日，这些作品依然是世界侦探文学宝库里最宝贵的财富。根据她的小说改编而成的舞台剧《捕鼠器》，已经成为世界上公演场次最多的剧目；而在影视改编方面，《东方快车谋杀案》为英格丽•褒曼斩获奥斯卡大奖，《尼罗河上的惨案》更是成为了几代人心目中的经典。\r\n\r\n阿加莎•克里斯蒂的创作生涯持续了五十余年，总共创作了八十部侦探小说。她的作品畅销全世界一百多个国家和地区，累计销量已经突破二十亿册。她创造的小胡子侦探波洛和老处女侦探马普尔小姐为读者津津乐道。阿加莎•克里斯蒂是柯南•道尔之后最伟大的侦探小说作家，是侦探文学黄金时代的开创者和集大成者。一九七一年，英国女王授予克里斯蒂爵士称号，以表彰其不朽的贡献。\r\n\r\n一九七六年一月十二日，阿加莎•克里斯蒂逝世于英国牛津郡沃灵福德家中，被安葬于牛津郡的圣玛丽教堂墓园，享年八十五岁。','作者的话\r\n第一章\r\n第二章\r\n第三章\r\n第四章\r\n第五章\r\n第六章\r\n第七章\r\n第八章\r\n第九章\r\n第十章\r\n第十一章\r\n第十二章\r\n第十三章\r\n第十四章\r\n第十五章\r\n第十六章\r\n尾声\r\n拖网渔船爱玛•珍号船主寄给苏格兰场的手稿'),(35,'普林斯顿微积分读本（修订版）','9787115435590',NULL,'人民邮电出版社',2016,99,10,NULL,'','The Calculus Lifesaver:All the Tools You Need to Excel at Calculus',1,668,'本书阐述了求解微积分的技巧, 详细讲解了微积分基础、极限、连续、微分、导数的应用、积分、无穷级数、泰勒级数与幂级数等内容，旨在教会读者如何思考问题从而找到解题 所需的知识点, 着重训练大家自己解答问题的能力.\r\n\r\n本书适用于大学低年级学生、高中高年级学生、想学习微积分的数学爱好者以及广大数 学教师. 本书既可作为教材、习题集, 也可作为学习指南, 同时还有利于教师备课.','阿德里安•班纳（Adrian Banner）\r\n\r\n澳大利亚新南威尔士大学数学学士及硕士，普里斯顿大学数学博士。2002年起任职于INTECH公司，现为INTECH公司首席执行官兼首席投资官。同时，他在普林斯顿大学教学数学系任兼职教师。','第1章　函数、图像和直线　　1\r\n1.1 函数　　1\r\n1.1.1 区间表示法　　3\r\n1.1.2 求定义域　　3\r\n1.1.3 利用图像求值域　　4\r\n1.1.4 垂线检验　　5\r\n1.2 反函数　　6\r\n1.2.1 水平线检验　　7\r\n1.2.2 求反函数　　8\r\n1.2.3 限制定义域　　8\r\n1.2.4 反函数的反函数　　9\r\n1.3 函数的复合　　10\r\n1.4 奇函数和偶函数　　12\r\n1.5 线性函数的图像　　14\r\n1.6 常见函数及其图像　　16\r\n第2章　三角学回顾　　21\r\n2.1 基本知识　　21\r\n2.2 扩展三角函数定义域　　23\r\n2.2.1 ASTC 方法　　25\r\n2.2.2 [0; 2π] 以外的三角函数　　27\r\n2.3 三角函数的图像　　29\r\n2.4 三角恒等式　　32\r\n第3章　极限导论　　34\r\n3.1 极限：基本思想　　34\r\n3.2 左极限与右极限　　36\r\n3.3 何时不存在极限　　37\r\n3.4 在∞ 和-∞ 处的极限　　38\r\n3.5 关于渐近线的两个常见误解　　41\r\n3.6 三明治定理　　43\r\n3.7 极限的基本类型小结　　45\r\n第4章　求解多项式的极限问题　　47\r\n4.1 x → a 时的有理函数的极限　　47\r\n4.2 x → a 时的平方根的极限　　50\r\n4.3 x → ∞ 时的有理函数的极限　　51\r\n4.4 x → ∞ 时的多项式型函数的极限　　56\r\n4.5 x → -∞ 时的有理函数的极限　　59\r\n4.6 包含绝对值的函数的极限　　61\r\n第5章　连续性和可导性　　63\r\n5.1 连续性　　63\r\n5.1.1 在一点处连续　　63\r\n5.1.2 在一个区间上连续　　64\r\n5.1.3 连续函数的一些例子　　65\r\n5.1.4 介值定理　　67\r\n5.1.5 一个更难的介值定理例子　　69\r\n5.1.6 连续函数的最大值和最小值　　70\r\n5.2 可导性　　71\r\n5.2.1 平均速率　　72\r\n5.2.2 位移和速度　　72\r\n5.2.3 瞬时速度　　73\r\n5.2.4 速度的图像阐释　　74\r\n5.2.5 切线　　75\r\n5.2.6 导函数　　77\r\n5.2.7 作为极限比的导数　　78\r\n5.2.8 线性函数的导数　　80\r\n5.2.9 二阶导数和更高阶导数　　80\r\n5.2.10 何时导数不存在　　81\r\n5.2.11 可导性和连续性　　82\r\n第6章　求解微分问题　　84\r\n6.1 使用定义求导　　84\r\n6.2 用更好的办法求导　　87\r\n6.2.1 函数的常数倍　　88\r\n6.2.2 函数和与函数差　　88\r\n6.2.3 通过乘积法则求积函数的导数　　88\r\n6.2.4 通过商法则求商函数的导数　　90\r\n6.2.5 通过链式求导法则求复合函数的导数　　91\r\n6.2.6 那个难以处理的例子　　94\r\n6.2.7 乘积法则和链式求导法则的理由　　96\r\n6.3 求切线方程　　98\r\n6.4 速度和加速度　　99\r\n6.5 导数伪装的极限　　101\r\n6.6 分段函数的导数　　103\r\n6.7 直接画出导函数的图像　　106\r\n第7章　三角函数的极限和导数　　111\r\n7.1 三角函数的极限　　111\r\n7.1.1 小数的情况　　111\r\n7.1.2 问题的求解——小数的情况　　113\r\n7.1.3 大数的情况　　117\r\n7.1.4 “其他的” 情况　　120\r\n7.1.5 一个重要极限的证明　　121\r\n7.2 三角函数的导数　　124\r\n7.2.1 求三角函数导数的例子　　127\r\n7.2.2 简谐运动　　128\r\n7.2.3 一个有趣的函数　　129\r\n第8章　隐函数求导和相关变化率　　132\r\n8.1 隐函数求导　　132\r\n8.1.1 技巧和例子　　133\r\n8.1.2 隐函数求二阶导　　137\r\n8.2 相关变化率　　138\r\n8.2.1 一个简单的例子　　139\r\n8.2.2 一个稍难的例子　　141\r\n8.2.3 一个更难的例子　　142\r\n8.2.4 一个非常难的例子　　144\r\n第9章　指数函数和对数函数　　148\r\n9.1 基础知识　　148\r\n9.1.1 指数函数的回顾　　148\r\n9.1.2 对数函数的回顾　　149\r\n9.1.3 对数函数、指数函数及反函数　　150\r\n9.1.4 对数法则　　151\r\n9.2 e 的定义　　153'),(36,'深入理解Java虚拟机（第2版）','9787111421900',NULL,'机械工业出版社',2013,79,9,1,'JVM高级特性与最佳实践','',1,433,'《深入理解Java虚拟机:JVM高级特性与最佳实践(第2版)》内容简介：第1版两年内印刷近10次，4家网上书店的评论近4?000条，98%以上的评论全部为5星级的好评，是整个Java图书领域公认的经典著作和超级畅销书，繁体版在台湾也十分受欢迎。第2版在第1版的基础上做了很大的改进：根据最新的JDK 1.7对全书内容进行了全面的升级和补充；增加了大量处理各种常见JVM问题的技巧和最佳实践；增加了若干与生产环境相结合的实战案例；对第1版中的错误和不足之处的修正；等等。第2版不仅技术更新、内容更丰富，而且实战性更强。\r\n\r\n《深入理解Java虚拟机:JVM高级特性与最佳实践(第2版)》共分为五大部分，围绕内存管理、执行子系统、程序编译与优化、高效并发等核心主题对JVM进行了全面而深入的分析，深刻揭示了JVM的工作原理。\r\n\r\n第一部分从宏观的角度介绍了整个Java技术体系、Java和JVM的发展历程、模块化，以及JDK的编译，这对理解书中后面内容有重要帮助。\r\n\r\n第二部分讲解了JVM的自动内存管理，包括虚拟机内存区域的划分原理以及各种内存溢出异常产生的原因；常见的垃圾收集算法以及垃圾收集器的特点和工作原理；常见虚拟机监控与故障处理工具的原理和使用方法。\r\n\r\n第三部分分析了虚拟机的执行子系统，包括类文件结构、虚拟机类加载机制、虚拟机字节码执行引擎。\r\n\r\n第四部分讲解了程序的编译与代码的优化，阐述了泛型、自动装箱拆箱、条件编译等语法糖的原理；讲解了虚拟机的热点探测方法、HotSpot的即时编译器、编译触发条件，以及如何从虚拟机外部观察和分析JIT编译的数据和结果；\r\n\r\n第五部分探讨了Java实现高效并发的原理，包括JVM内存模型的结构和操作；原子性、可见性和有序性在Java内存模型中的体现；先行发生原则的规则和使用；线程在Java语言中的实现原理；虚拟机实现高效并发所做的一系列锁优化措施。','周志明，资深Java技术专家，对JavaEE企业级应用开发、OSGi、Java虚拟机和工作流等都有深入的研究，并在大量的实践中积累了丰富的经验。尤其精通Java虚拟机，撰写了大量与JVM相关的经典文章，被各大技术社区争相转载，是ITeye等技术社区公认的Java虚拟机方面的领袖人物之一。除本书外，还著有经典著作《深入理解OSGi：Equinox原理、应用与最佳实践》，广获读者好评。现任远光软件股份有限公司开发部总经理兼架构师，先后参与过国家电网、南方电网等多个国家级大型ERP项目的平台架构工作，对软件系统架构也有深刻的认识和体会。','前言\r\n第一部分走近Java\r\n第1章走近Java2\r\n1.1概述2\r\n1.2Java技术体系3\r\n1.3Java发展史5\r\n1.4Java虚拟机发展史9\r\n1.4.1SunClassicExactVM9\r\n1.4.2SunHotSpotVM11\r\n1.4.3SunMobile—EmbeddedVMMeta—CircularVM12\r\n1.4.4BEAJRockitIBMJ9VM13\r\n1.4.5AzulVMBEALiquidVM14\r\n1.4.6ApacheHarmonyGoogleAndroidDalvikVM14\r\n1.4.7MicrosoftJVM及其他15\r\n1.5展望Java技术的未来16\r\n1.5.1模块化17\r\n1.5.2混合语言17\r\n1.5.3多核并行19\r\n1.5.4进一步丰富语法20\r\n1.5.564位虚拟机21\r\n1.6实战：自己编译JDK22\r\n1.6.1获取JDK源码22\r\n1.6.2系统需求24\r\n1.6.3构建编译环境25\r\n1.6.4进行编译26\r\n1.6.5在IDE工具中进行源码调试31\r\n1.7本章小结35\r\n第二部分自动内存管理机制\r\n第2章Java内存区域与内存溢出异常38\r\n2.1概述38\r\n2.2运行时数据区域38\r\n2.2.1程序计数器39\r\n2.2.2Java虚拟机栈39\r\n2.2.3本地方法栈40\r\n2.2.4Java堆41\r\n2.2.5方法区41\r\n2.2.6运行时常量池42\r\n2.2.7直接内存43\r\n2.3HotSpot虚拟机对象探秘43\r\n2.3.1对象的创建44\r\n2.3.2对象的内存布局47\r\n2.3.3对象的访问定位48\r\n2.4实战：OutOfMemoryError异常50\r\n2.4.1Java堆溢出51\r\n2.4.2虚拟机栈和本地方法栈溢出53\r\n2.4.3方法区和运行时常量池溢出56\r\n2.4.4本机直接内存溢出59\r\n2.5本章小结60\r\n第3章垃圾收集器与内存分配策略61\r\n3.1概述61\r\n3.2对象已死吗62\r\n3.2.1引用计数算法62\r\n3.2.2可达性分析算法64'),(37,'深入理解计算机系统（原书第2版）','9787111321330',NULL,'机械工业出版社',2011,99,1,1,'','Computer Systems: A Programmer\'s Perspective',1,702,'本书从程序员的视角详细阐述计算机系统的本质概念，并展示这些概念如何实实在在地影响应用程序的正确性、性能和实用性。全书共12章，主要内容包括信息的表示和处理、程序的机器级表示、处理器体系结构、优化程序性能、存储器层次结构、链接、异常控制流、虚拟存储器、系统级I/O、网络编程、并发编程等。书中提供大量的例子和练习，并给出部分答案，有助于读者加深对正文所述概念和知识的理解。\r\n\r\n本书的最大优点是为程序员描述计算机系统的实现细节，帮助其在大脑中构造一个层次型的计算机系统，从最底层的数据在内存中的表示到流水线指令的构成，到虚拟存储器，到编译系统，到动态加载库，到最后的用户态应用。通过掌握程序是如何映射到系统上，以及程序是如何执行的，读者能够更好地理解程序的行为为什么是这样的，以及效率低下是如何造成的。\r\n\r\n本书适合那些想要写出更快、更可靠程序的程序员阅读，也适合作为高等院校计算机及相关专业本科生、研究生的教材。','Randal E.Bryant　1973年于密歇根大学（University of Michigan）获得学士学位，随即就读于麻省理工学院的研究生院，并在1981年获计算机博士学位。他在加州理工学院（California Institute of Technology）做了三年助教，从1984年至今一直是卡内基-梅隆大学的教师。他现在是计算机科学的大学教授和计算机科学学院的院长。他同时还受邀于电子和计算机工程系。\r\n\r\n他从事本科生和研究生计算机系统方面课程的教学超过30年。在讲授计算机体系结构课程多年后，他开始把关注点从如何设计计算机转移到程序员如何在更好的了解系统的情况下编写出更有效和更可靠的程序。他和O’Hallaron教授一起在卡内基梅隆大学开设了15-213“计算机系统导论”课程，那便是此书的基础。他还教授一些有关算法、编程、计算机网络和VLSI（超大规模集成电路）设计方面的课程。\r\n\r\nBryant教授的主要研究内容是设计软件工具来帮助软件和硬件设计者验证其系统正确性。其中，包括几种类型的模拟器，以及用数学方法来证明设计正确性的形式化验证工具。他发表了150多篇技术论文。包括Intel、FreeScale、IBM和Fujitsu在内的主要计算机制造商都使用着他的研究成果。他还因他的研究获得过数项大奖。其中包括Semiconductor Research Corporation颁发的两个发明荣誉奖和一个技术成就奖，ACM颁发的Kanellakis理论与实践奖，还有IEEE授予的W.R.G.Baker奖、Emmanuel Piore奖和Phil Kaufman奖。他还是ACM院士、IEEE院士和美国国家工程院院士。\r\n\r\nDavid R.O’Hallaron 现为Intel匹兹堡实验室主任，卡内基-梅隆大学电子和计算机工程系副教授。在弗吉尼亚大学获得计算机科学的博士学位。\r\n\r\n他教授本科生和研究生的计算机系统方面的课程，例如计算机体系结构、计算机系统导论、并行处理器设计和Internet服务。他和Bryant教授一起开设了“计算机系统导论”课程，那便是此书的基础。2004年他获得了CMU计算机学院颁发的Herbert Simon杰出教学奖，这个奖项的获得者是基于学生的投票产生的。\r\n\r\nO’Hallaron教授从事计算机系统领域的研究，主要兴趣在于科学计算、数据密集型计算和虚拟化方面的软件系统。其中最著名的是Quake项目，一群计算机科学家、土木工程师和地震学家致力于提高对强烈地震中大地运动的预测能力。2003年，他同Quake项目中其他成员一起获得了高性能计算领域中的最高国际奖项—Gordon Bell奖。','出版者的话\r\n译者序\r\n前　言\r\n第1章　计算机系统漫游1\r\n1.1　信息就是位+上下文1\r\n1.2　程序被其他程序翻译成不同的格式3\r\n1.3　了解编译系统如何工作是大有益处的4\r\n1.4　处理器读并解释存储在存储器中的指令5\r\n1.4.1　系统的硬件组成5\r\n1.4.2　运行hello程序7\r\n1.5　高速缓存至关重要7\r\n1.6　存储设备形成层次结构9\r\n1.7　操作系统管理硬件10\r\n1.7.1　进程11\r\n1.7.2　线程12\r\n1.7.3　虚拟存储器12\r\n1.7.4　文件13\r\n1.8　系统之间利用网络通信13\r\n1.9　重要主题15\r\n1.9.1　并发和并行15\r\n1.9.2　计算机系统中抽象的重要性17\r\n1.10　小结17\r\n参考文献说明18\r\n第一部分　程序结构和执行\r\n第2章　信息的表示和处理20\r\n2.1　信息存储22\r\n2.1.1　十六进制表示法22\r\n2.1.2　字25\r\n2.1.3　数据大小25\r\n2.1.4　寻址和字节顺序26\r\n2.1.5　表示字符串31\r\n2.1.6　表示代码31\r\n2.1.7　布尔代数简介32\r\n2.1.8　C语言中的位级运算34\r\n2.1.9　C语言中的逻辑运算36\r\n2.1.10　C语言中的移位运算36\r\n2.2　整数表示38\r\n2.2.1　整型数据类型38\r\n2.2.2　无符号数的编码39\r\n2.2.3　补码编码40\r\n2.2.4　有符号数和无符号数之间的转换44\r\n2.2.5　C语言中的有符号数与无符号数47\r\n2.2.6　扩展一个数字的位表示49\r\n2.2.7　截断数字51\r\n2.2.8　关于有符号数与无符号数的建议52\r\n2.3　整数运算54\r\n2.3.1　无符号加法54\r\n2.3.2　补码加法57\r\n2.3.3　补码的非59\r\n2.3.4　无符号乘法60\r\n2.3.5　补码乘法60\r\n2.3.6　乘以常数63\r\n2.3.7　除以2的幂64\r\n2.3.8　关于整数运算的最后思考67\r\n2.4　浮点数67\r\n2.4.1　二进制小数68\r\n2.4.2　IEEE浮点表示70\r\n2.4.3　数字示例71\r\n2.4.4　舍入74\r\n2.4.5　浮点运算76\r\n2.4.6　C语言中的浮点数77\r\n2.5　小结79\r\n参考文献说明80\r\n家庭作业80\r\n练习题答案90\r\n第3章　程序的机器级表示102\r\n3.1　历史观点103\r\n3.2　程序编码105\r\n3.2.1　机器级代码106\r\n3.2.2　代码示例107\r\n3.2.3　关于格式的注解109\r\n3.3　数据格式111\r\n3.4　访问信息112\r\n3.4.1　操作数指示符112\r\n3.4.2　数据传送指令114\r\n3.4.3　数据传送示例116\r\n3.5　算术和逻辑操作118\r\n3.5.1　加载有效地址118\r\n3.5.2　一元操作和二元操作119\r\n3.5.3　移位操作120\r\n3.5.4　讨论120\r\n3.5.5　特殊的算术操作122\r\n3.6　控制123\r\n3.6.1　条件码124\r\n3.6.2　访问条件码125\r\n3.6.3　跳转指令及其编码127\r\n3.6.4　翻译条件分支129\r\n3.6.5　循环132\r\n3.6.6　条件传送指令139\r\n3.6.7　switch语句144\r\n3.7　过程149\r\n3.7.1　栈帧结构149\r\n3.7.2　转移控制150\r\n3.7.3　寄存器使用惯例151\r\n3.7.4　过程示例152\r\n3.7.5　递归过程156\r\n3.8　数组分配和访问158\r\n3.8.1　基本原则158\r\n3.8.2　指针运算159\r\n3.8.3　嵌套的数组159\r\n3.8.4　定长数组161\r\n3.8.5　变长数组163\r\n3.9　异质的数据结构164\r\n3.9.1　结构164\r\n3.9.2　联合167\r\n3.9.3　数据对齐170\r\n3.10　综合：理解指针172\r\n3.11　应用：使用GDB调试器174\r\n3.12　存储器的越界引用和缓冲区溢出175\r\n3.13　x86-64：将IA32扩展到64位183\r\n3.13.1　x86-64的历史和动因184\r\n3.13.2　x86-64简介185\r\n3.13.3　访问信息187\r\n3.13.4　控制192\r\n3.13.5　数据结构200\r\n3.13.6　关于x86-64的总结性评论200\r\n3.14　浮点程序的机器级表示201\r\n3.15　小结201\r\n参考文献说明202');
/*!40000 ALTER TABLE `t_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_book_author`
--

DROP TABLE IF EXISTS `t_book_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_book_author` (
  `book_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_book_author`
--

LOCK TABLES `t_book_author` WRITE;
/*!40000 ALTER TABLE `t_book_author` DISABLE KEYS */;
INSERT INTO `t_book_author` VALUES (1,5),(1,6),(2,2),(2,3),(2,4),(3,1),(4,5),(25,10),(27,10),(30,11),(31,12),(32,7),(35,13),(36,14),(37,15),(37,16);
/*!40000 ALTER TABLE `t_book_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_book_translator`
--

DROP TABLE IF EXISTS `t_book_translator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_book_translator` (
  `book_id` int(11) NOT NULL,
  `translator_id` int(11) NOT NULL,
  PRIMARY KEY (`book_id`,`translator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_book_translator`
--

LOCK TABLES `t_book_translator` WRITE;
/*!40000 ALTER TABLE `t_book_translator` DISABLE KEYS */;
INSERT INTO `t_book_translator` VALUES (1,1),(1,2),(3,3),(4,4),(4,5),(25,7),(27,7),(31,8),(32,9),(35,10),(35,11),(35,12),(37,13),(37,14);
/*!40000 ALTER TABLE `t_book_translator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_book_user`
--

DROP TABLE IF EXISTS `t_book_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_book_user` (
  `book_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '标记类型',
  `score` smallint(2) DEFAULT NULL COMMENT '评分',
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`book_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_book_user`
--

LOCK TABLES `t_book_user` WRITE;
/*!40000 ALTER TABLE `t_book_user` DISABLE KEYS */;
INSERT INTO `t_book_user` VALUES (1,2,1,NULL,'2020-01-10 13:24:31'),(2,1,2,10,'2020-01-10 13:24:31'),(2,2,1,8,'2020-01-10 13:24:31'),(3,2,2,10,'2020-01-10 13:24:31');
/*!40000 ALTER TABLE `t_book_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_translator`
--

DROP TABLE IF EXISTS `t_translator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_translator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL COMMENT '译者名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_translator`
--

LOCK TABLES `t_translator` WRITE;
/*!40000 ALTER TABLE `t_translator` DISABLE KEYS */;
INSERT INTO `t_translator` VALUES (1,'王垒'),(2,'王甦'),(3,'柳鸣九'),(4,'孙佩妏'),(5,'陈雅馨'),(7,'邢世阳'),(8,'刘姿君'),(9,'夏阳'),(10,'杨爽'),(11,'赵晓婷'),(12,'高璞'),(13,'龚奕利'),(14,'雷迎春');
/*!40000 ALTER TABLE `t_translator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '可用状态',
  `nickname` varchar(45) DEFAULT NULL,
  `avatar` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `avatar_UNIQUE` (`avatar`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'clio','$2a$10$OaExo/3Us/rhx/zJais8..HbFSI6S35BWswLswHdvjrwUN4oWIm5W',1,'h','788b3ca6-a8c3-400c-92a9-62d4a5fd680f.jpg'),(2,'c','$2a$10$.Pkblm5dhEnB1Hm6SJOhneQz6CJwSZLORZxY44gkumUHUBwmwCruS',1,'电鳗ss','1b8d09a0-25d9-4c7e-ac5c-69957711ecf4.png'),(4,'a','$2a$10$ywvEmJFiwT3.xDp0CgejbuYze5vKRSjto8HINydBA/azTQ8Q7mYcO',1,NULL,NULL),(6,'e','$2a$10$aO.fD.zThGfmc562Aps6k..kP0aT3/4Hkn561ZvggT1viFL8hjDgW',1,NULL,NULL),(7,'f','$2a$10$MyYgfby34w11jDWXtpRdqOpbEhsohfV6uG2KzNkJ9kH2pnwgjVqrS',1,'','6c60a376-8451-492d-901e-b513f6208aaf.png'),(8,'h','$2a$10$KcXrKzfnc1kMBce34iYVZ.7yrUATCPQOBMHCC.Ci4oPU/5pvVNOWq',1,'电鳗s','026e5a03-8434-4d82-b5b5-c2ad60d004a9.png'),(9,'j','$2a$10$M6Ob2G2q9D4AT7ULrAoiROTaH2kHJP3ODStjRcWEQR8j0gYF1TZg.',1,'j','5cb491db-33d5-4c1a-83df-1273ab2d86db.png');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v_book_author`
--

DROP TABLE IF EXISTS `v_book_author`;
/*!50001 DROP VIEW IF EXISTS `v_book_author`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `v_book_author` AS SELECT 
 1 AS `book_id`,
 1 AS `book_name`,
 1 AS `author_name`,
 1 AS `book_isbn`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_book_author_translator`
--

DROP TABLE IF EXISTS `v_book_author_translator`;
/*!50001 DROP VIEW IF EXISTS `v_book_author_translator`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `v_book_author_translator` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `isbn`,
 1 AS `picture`,
 1 AS `publisher`,
 1 AS `publication_year`,
 1 AS `price`,
 1 AS `publication_month`,
 1 AS `publication_day`,
 1 AS `subtitle`,
 1 AS `original_name`,
 1 AS `binding`,
 1 AS `page`,
 1 AS `content_intro`,
 1 AS `author_intro`,
 1 AS `directory`,
 1 AS `author_id`,
 1 AS `author_name`,
 1 AS `translator_id`,
 1 AS `translator_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_book_author`
--

/*!50001 DROP VIEW IF EXISTS `v_book_author`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_book_author` AS select `t_book`.`id` AS `book_id`,`t_book`.`name` AS `book_name`,`t_author`.`name` AS `author_name`,`t_book`.`isbn` AS `book_isbn` from ((`t_book_author` join `t_book` on((`t_book_author`.`book_id` = `t_book`.`id`))) join `t_author` on((`t_book_author`.`author_id` = `t_author`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_book_author_translator`
--

/*!50001 DROP VIEW IF EXISTS `v_book_author_translator`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_book_author_translator` AS select `t_book`.`id` AS `id`,`t_book`.`name` AS `name`,`t_book`.`isbn` AS `isbn`,`t_book`.`picture` AS `picture`,`t_book`.`publisher` AS `publisher`,`t_book`.`publication_year` AS `publication_year`,`t_book`.`price` AS `price`,`t_book`.`publication_month` AS `publication_month`,`t_book`.`publication_day` AS `publication_day`,`t_book`.`subtitle` AS `subtitle`,`t_book`.`original_name` AS `original_name`,`t_book`.`binding` AS `binding`,`t_book`.`page` AS `page`,`t_book`.`content_intro` AS `content_intro`,`t_book`.`author_intro` AS `author_intro`,`t_book`.`directory` AS `directory`,`t_author`.`id` AS `author_id`,`t_author`.`name` AS `author_name`,`t_translator`.`id` AS `translator_id`,`t_translator`.`name` AS `translator_name` from ((((`t_book` join `t_book_author` on((`t_book_author`.`book_id` = `t_book`.`id`))) join `t_author` on((`t_author`.`id` = `t_book_author`.`author_id`))) left join `t_book_translator` on((`t_book_translator`.`book_id` = `t_book`.`id`))) left join `t_translator` on((`t_translator`.`id` = `t_book_translator`.`translator_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-10 13:25:51
