package com.mmo.frame.net;

import com.google.protobuf.Parser;
import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 协议枚举
 * Created by miaopeng on 4-17
 */
public enum ProtoEnum {
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    //||||||||||||global和外网http之间的通信协议||||||||||||||||\\
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    /**
     * http客户端请求分配guid
     */
    P_HTTPCLIENT_GLOBAL_REQUEST_GUID(-1, null),
    /**
     * 将分配的guid发送给http客户端
     */
    P_GLOBAL_HTTPCLIENT_ALLOCATE_GUID(-2, null),
    /**
     * 默认出错返回的结果
     */
    P_GLOBAL_HTTPCLIENT_DEFAULT_RESULT(-3, null),
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    //||||||||||||global和内网服务器之间的通信协议||||||||||||||||\\
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    /**
     * 请求分配一个guid
     */
    P_RPCCLIENT_GLOBAL_ALLOCATE_GUID(-4, null),
    /**
     * 返回请求的guid
     */
    P_GLOBAL_RPCCLIENT_ALLOCATE_GUID_RESULT(-5, null),
    /**
     * 返回分配的guid
     */
    P_GLOBAL_RPCCLIENT_ALLOCATE_GUID(-6, null),
    /**
     * 通过场景进程id返回对应配置的地图id列表
     */
    P_RPCCLIENT_GLOBAL_QUERY_SCENE_MAP_ARRAY(-7, null),
    /**
     * 根据服id查找到挂在在改服之下所有的场景进程id(该id是数据库索引)
     */
    P_RPCCLIENT_GLOBAL_QUERY_SCENE_PROCESSINDEX_ARRAY(-8, null),
    /**
     * 返回场景进程id(该id是数据库索引)
     */
    P_GLOBAL_RPCCLIENT_QUERY_SCENE_PROCESSINDEX_ARRAY_RESULT(-9, null),
    /**
     * 通过玩家服id找到实际对应的服id
     */
    P_RPCCLIENT_GLOBAL_QUERY_ACTUAL_SERVER_ID(-10, null),
    /**
     * 返回实际对应的服id
     */
    P_GLOBAL_RPCCLIENT_QUERY_ACTUAL_SERVER_ID_RESULT(-11, null),
    /**
     * 通过服id找到数据库的连接
     */
    P_RPCCLIENT_GLOBAL_GET_DBURL_BY_ACTUAL_SERVERID(-12, null),
    /**
     * 返回数据库连接信息
     */
    P_GLOBAL_RPCCLIENT_GET_DBURL_BY_ACTUAL_SERVERID_RESULT(-13, null),
    /**
     * 请求global的tcp连接的端口信息
     */
    P_RPCCLIENT_GLOBAL_GET_GLOBAL_TCP_CONNECTION_INFO(-14, null),
    /**
     * 返回globaltcp连接信息
     */
    P_GLOBAL_RPCCLIENT_RETURN_TCP_CONNECTION_INFO(-15, null),
    /**
     * 请求场景信息
     */
    P_RPCCLIENT_GLOBAL_REQUEST_SCENE_INFO(-16, null),
    /**
     * 返回场景信息结果
     */
    P_GLOBAL_RPCCLIENT_REQUEST_SCENE_INFO_RESULT(-17, null),
    /**
     * sm已经完成停服的所有准备工作通知global
     */
    P_RPCCLIENT_GLOBAL_SM_SHUTDOWN_OVER(-18, null),
    /**
     *
     */
    P_GLOBAL_RPCCLIENT_SM_SHUTDOWN_OVER_RESULT(-19, null),
    /**
     * 关联场景进程和sm进程
     */
    P_RPCCLIENT_GLOBAL_SCENE_SM_LINK(-20, null),
    /**
     *
     */
    P_GLOBAL_RPCCLIENT_SCENE_SM_LINK_RESULT(-21, null),
    /**
     *
     */
    P_RPCCLIENT_GLOBAL_START_SCENE_REGIST_GLOBAL(-22, null),
    /**
     *
     */
    P_GLOBAL_RPCCLIENT_START_SCENE_REGIST_GLOBAL_RESULT(-23, null),
    /**
     * 验证global服务已经启动
     */
    P_RPCCLIENT_GLOBAL_MAKE_SURE_GLOBAL_SERVICE_AVAIABLE(-24, null),
    /**
     * 返回确认global服务已经启动
     */
    P_GLOBAL_RPCCLIENT_MAKE_SURE_GLOBAL_SERVICE_AVAIABLE_RESULT(-25, null),
    /**
     * 获取全局属性
     */
    P_RPCCLIENT_GLOBAL_GET_PROPERTY(-26, null),
    /**
     * 返回全局属性
     */
    P_GLOBAL_RPCCLIENT_GET_PROPERTY_RESULT(-27, null),

    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    //||||||||||||global和guard服务器之间的通信协议||||||||||||||||\\
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    /**
     * guard将自己注册到global
     */
    P_GUARD_GLOBAL_REGISTER(-28, null),
    /**
     * global对guard发ping包
     */
    P_GLOBAL_GUARD_PING(-29, null),
    /**
     * guard对global发ping包
     */
    P_GUARD_GLOBAL_PING(-30, null),
    /**
     * 关闭某个服
     */
    P_GUARD_GLOBAL_SHUTDOWN_SERVER(-31, null),
    /**
     * global接收到关闭指定服的协议(-1,null),但是这个服不存在(-1,null),返回这条消息
     */
    P_GLOBAL_GUARD_SHUTDOWN_SERVER_NOT_EXIT(-32, null),
    /**
     * guard传送命令给global请求全服停服
     */
    P_GUARD_GLOBAL_SHUTDOWN(-33, null),
    /**
     * guard传送命令给global请求服务器列表信息
     */
    P_GUARD_GLOBAL_REQUEST_SERVER_LIST(-34, null),
    /**
     * global将服务器分布个数信息发送给guard
     */
    P_GLOBAL_GUARD_SEND_SERVER_NUM(-35, null),
    /**
     * 通知所有guard某一个服关服了
     */
    P_GLOBAL_GUARD_SERVER_SHUTDOWN_OVER(-36, null),
    /**
     * guard命令global关闭进程
     */
    P_GUARD_GLOBAL_COMMAND_CLOSE_PROCESS(-37, null),
    /**
     * 查询所有服的人数分布信息
     */
    P_GUARD_GLOBAL_SHOW_PLAYER_NUM_INFO(-38, null),
    /**
     * 返回游戏世界内的在线人数
     */
    P_GLOBAL_GUARD_SHOW_STR_IN_GLOBAL_CONSOLE(-39, null),
    /**
     * 当前global正在统计过程中海没有统计完成
     */
    P_GLOBAL_GUARD_SHOW_PLAYER_NUM_INFO_PENDING(-40, null),
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    //||||||||||||global和auth服务器之间的通信协议||||||||||||||||\\
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    /**
     * AUTH将自己注册到global
     */
    P_AUTH_GLOBAL_REGISTER(-41, null),
    /**
     * global通知auth关服
     */
    P_GLOBAL_AUTH_COMMAND_SHUTDOWN(-42, null),
    /**
     * global向auth进行ping
     */
    P_GLOBAL_AUTH_PING(-43, null),
    /**
     * auth向global进行ping
     */
    P_AUTH_GLOBAL_PING(-44, null),
    /**
     * auth向global发送角色登陆请求协议
     */
    P_AUTH_GLOBAL_LOGIN_A_ROLE(-45, null),
    /**
     * auth为某角色登陆对应的服没有启动
     */
    P_GLOBAL_AUTH_LOGIN_SM_NOT_EXIST(-46, null),
    /**
     * 该玩家在sm被锁定了(-1,null),登陆失败
     */
    P_GLOBAL_AUTH_LOGIN_A_PLAYER_STATE_LOCKED(-47, null),
    /**
     * 返回登陆验证信息以及客户端需要连接的地图服务器信息
     */
    P_GLOBAL_AUTH_RETURN_PLAYER_LOGIN_TOKEN_BACK(-48, null),
    /**
     * 创建角色协议发送给auth
     */
    P_AUTH_GLOBAL_CREATE_ROLE(-49, null),
    /**
     * 返回创建角色成功协议
     */
    P_GLOBAL_AUTH_CREATE_ROLE_SUCCESS(-50, null),
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    //||||||||||||地图服务器scene和sm服务器之间的通信协议||||||||||||||||\\
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\

    /**
     * sm起服的时候将自己连接到scene
     */
    P_SM_SCENE_REGISTER(-51, null),
    /**
     * 场景scene将自己注册到sm服务器
     */
    P_SCENE_SM_REGISTER(-52, null),
    /**
     * 场景服向sm进行ping
     */
    P_SCENE_SM_PING(-53, null),
    /**
     * sm向场景服ping
     */
    P_SM_SCENE_PING(-54, null),
    /**
     * 地图服务器向sm服务器同步玩家数据的协议
     */
    P_SCENE_SM_SYNC_PLAYER_INFO(-55, null),
    /**
     * scene服中场景中的玩家超时
     */
    P_SCENE_SM_NOTIFY_SCENE_PLAYER_TIME_OUT(-56, null),
    /**
     * 将在线玩家下线
     */
    P_SM_SCENE_COMMAND_PLAYER_OFF_LINE(-57, null),
    /**
     * sm发送给scene一个跟踪事务的事件
     */
    P_SM_SCENE_TRACE_A_CALL_BACK_EVENT(-58, null),
    /**
     * 返回跟踪完成的事务
     */
    P_SCENE_SM_TRACE_A_CALL_BACK_EVENT_BACK(-59, null),
    /**
     * 请求登陆到某个城镇
     */
    P_SCENE_SM_LOGIN_2_TOWN(-60, null),
    /**
     * 玩家登陆城镇成功
     */
    P_SM_SCENE_LOGIN_2_TOWN_SUCCESS(-61, null),
    /**
     * 玩家传图成功(-1,null),将玩家传送到地图服务器
     */
    P_SM_SCENE_TRANSFER_2_TOWN_SUCCESS(-62, null),
    /**
     * 玩家传送进入副本成功
     */
    P_SM_SCENE_TRANSFER_2_OUTLAND_SUCCESS(-63, null),
    /**
     * 登陆失败的错误码
     */
    P_SM_SCENE_CLEAN_UP_WAIT_SM_INFO(-64, null),
    /**
     * 请求传送到城镇
     */
    P_SCENE_SM_REQUEST_TRANSFER_2_TOWN(-65, null),
    /**
     * 客户端登陆场景服验证
     */
    P_SCENE_SM_CLIENT_JOIN_MAP_VERIFY(-66, null),
    /**
     * 传图验证错误信息
     */
    P_SM_SCENE_SWITCH_MAP_ERROR(-67, null),
    /**
     * 传图第一步要将玩家的数据从场景删除
     */
    P_SM_SCENE_CLEAN_SCENE_PLAYER_FOR_SWITH_MAP(-68, null),
    /**
     * 将传图信息通知地图服务器进一步通知客户端
     */
    P_SM_SCENE_COMMAND_SWITCH_MAP(-69, null),
    /**
     * 将sm对应服的分线信息传送到scene
     */
    P_SM_SCENE_SYNC_MAP_LINE_DATA(-70, null),
    /**
     * 客户端请求切线
     */
    P_SCENE_SM_REQUEST_CHANGE_LINE(-71, null),
    /**
     * 客户端所请求切的线不存在
     */
    P_SCENE_SM_CHANGE_TARGET_LINE_NOT_EXIST(-72, null),
    /**
     * sm通知某一个场景创建一个副本
     */
    P_SM_SCENE_CREATE_OUTLAND_BY_GUID(-73, null),
    /**
     * 场景上检测到一个副本过期(-1,null),通知sm移除了该副本
     */
    P_SCENE_SM_REMOVE_OUTLAND(-74, null),
    /**
     * 向sm挂载的所有的场景进程广播世界聊天消息
     */
    P_SCENE_SM_SYNC_CHAT_PROTO_TO_ALL_SCENE(-75, null),
    /**
     * sm通知某一个场景，向场景中的所有玩家广播世界聊天消息
     */
    P_SM_SCENE_SYNC_CHAT_PROTO_TO_ALL_PLAYER(-76, null),
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    //||||||||||||客户端和地图服务器之间的通信协议||||||||||||||||\\
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\

    /**
     * SM将自己注册到global服务器
     */
    P_SM_GLOBAL_REGISTER(-77, null),
    /**
     * sm通知global单个服启动成功
     */
    P_SM_GLOBAL_SINGLE_SERVER_START_SUCCESS(-78, null),
    /**
     * global通知sm关服
     */
    P_GLOBAL_SM_COMMAND_SHUTDOWN(-79, null),
    /**
     * global向所有sm进行ping
     */
    P_GLOBAL_SM_PING(-80, null),
    /**
     * sm向global发送ping
     */
    P_SM_GLOBAL_PING(-81, null),
    /**
     * 通知global某一个区的空闲场景服宕机了
     */
    P_SM_GLOBAL_NOTIFY_AN_ADLE_SCENE_CRASH(-82, null),
    /**
     * 通知global某一个区场景服务器宕机了
     */
    P_SM_GLOBAL_NOTIFY_AREA_SCENE_CRASH(-83, null),
    /**
     * 通知global某一个服的场景服宕机了
     */
    P_SM_GLOBAL_NOTIFY_SERVER_SCENE_CRASH(-84, null),
    /**
     * 由于服场景进程宕机(-1,null),服场景进程已经没有可用的服务器进行恢复的时候(-1,null),停止该服
     */
    P_SM_GLOBAL_NOTIFY_SERVER_SCENE_CRASH_LEAD_TO_SERVER_SHUTDOWN(-85, null),
    /**
     * 告知sm有一個角色登陸該服
     */
    P_GLOBAL_SM_LOGIN_A_PLAYER(-86, null),
    /**
     * 返回玩家登陆信息
     */
    P_SM_GLOBAL_RETURN_PLAYER_LOGIN_TOKEN_BACK(-87, null),
    /**
     * 将创建角色请求发送到sm
     */
    P_GLOBAL_SM_CREATE_ROLE(-88, null),
    /**
     * 返回创建角色成功协议
     */
    P_SM_GLOBAL_CREATE_ROLE_SUCCESS(-89, null),
    /**
     * global请求sm人数分布情况
     */
    P_GLOBAL_SM_SHOW_PLAYER_NUM_INFO(-90, null),
    /**
     * sm将在线人数分布返回给global
     */
    P_SM_GLOBAL_SHOW_PLAYER_NUM_INFO_RESULT(-91, null),
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    //||||||||||||客户端和auth服务器之间的通信协议||||||||||||||||\\
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    /**
     * 客户端登陆协议
     */
    //P_CLIENT_AUTH_LOGIN(-92,null),
    /**
     * 客户端登陆时发送过来的账号为空(-1,null),返回给客户端
     */
    P_AUTH_CLIENT_LOGIN_ACCOUNT_EMPTY(-93, null),
    /**
     * 客户端所登陆的服不存在
     */
    P_AUTH_CLIENT_SERVER_NOT_EXIST(-94, null),
    /**
     * 命令客户端创建角色
     */
    //P_AUTH_CLIENT_COMMAND_CREATE_ROLE(-95,null),
    /**
     * 玩家所登陆的服务器现在处于停服状态
     */
    P_AUTH_CLIENT_SM_NOT_EXIST(-96, null),
    /**
     * auth服务器命令客户端连接某个地图场景服务器
     */
    P_AUTH_CLIENT_COMMAND_CONNECT_SCENE(-97, null),
    /**
     * 请求创建一个角色
     */
    P_CLIENT_AUTH_REGISTER_A_ROLE(-98, null),
    /**
     * 客户端起的名字太短
     */
    P_AUTH_CLIENT_ROLE_NAME_LEN_NOT_ENOUGTH(-99, null),
    /**
     * 客户端起的名字太长
     */
    P_AUTH_CLIENT_ROLE_NAME_LEN_TOO_LARGE(-100, null),
    /**
     * 客户端起的名字为空
     */
    P_AUTH_CLIENT_ROLE_NAME_EMPTY(-101, null),
    /**
     * 客户端的名字已经存在了
     */
    P_AUTH_CLIENT_ROLE_NAME_ALREAY_EXIST(-102, null),
    /**
     * 客户端名字包含非法字段
     */
    P_AUTH_CLIENT_ROLE_NAME_CONTAIN_ILLEGAL_STRING(-103, null),
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    //||||||||||||客户端和地图服务器之间的通信协议||||||||||||||||\\
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    /**
     * 客户端向服务器发送在场景中走路的协议
     */
    P_CLIENT_SCENE_MOVE_IN_MAP(-105, null),
    /**
     * 角色移动完成告诉服务器自己的坐标
     */
    P_CLIENT_SCENE_STOP_MOVER(-106, null),
    /**
     * 服务器矫正客户端玩家自己的位置坐标(-1,null),客户端收到这条协议之后应该清除当前走路的状态
     */
    P_SCENE_CLIENT_CORRECT_GAME_OBJECT_POSTION(-107, null),
    /**
     * 服务器通知客户端某一个场景内的单位进行移动
     */
    P_SCENE_CLIENT_GAME_OBJECT_MOVE(-108, null),

    /**
     * 同步玩家数据
     */
    P_SCENE_CLIENT_SYNC_PLAYER_INFO(-110, null),
    /**
     * 只在玩家进入任何场景发送一次的协议(-1,null),客户端根据此协议创建场景
     */
    P_SCENE_CLIENT_COMMAND_PLAYER_CREATE_SCENE(-111, null),
    /**
     * 刷新场景时告诉玩家pk模式、阵营等信息
     */
    P_SCENE_CLIENT_SYNC_PLAYER_PK_INFO(-112, null),
    /**
     * 通知玩家惩恶系统灰红名信息
     */
    P_SCENE_CLIENT_SYNC_PLAYER_EVIL_NAME_INFO(-113, null),
    /**
     * 通知玩家地图信息
     */
    P_SCENE_CLIENT_SYNC_MAP_INFO(-114, null),
    /**
     * 告诉玩家视范围内新增游戏对象
     */
    P_SCENE_CLIENT_SYNC_SCENE_GAME_OBJECTS_INFO(-115, null),
    /**
     * 将失去视野的格子中的游戏对象同步给我自己
     */
    P_SCENE_CLIENT_SYNC_SCENE_GAME_OBJECTS_LEAVE(-116, null),

    /**
     * 玩家下线
     */
    P_SCENE_CLIENT_OFF_LINE(-118, null),
    /**
     * 别人登陆验证把你T掉了
     */
    P_SCENE_CLIENT_KICKED_OUT_FOR_AUTH_REASON(-119, null),
    /**
     * 服务器发送给客户端严重错误异常警告(-1,null),客户端收到此协议切换到初始界面
     * 造成此类消息的情况时基于多端操作同一个账号所引起的几率很小的边界情况
     */
    P_SCENE_CLIENT_SERIOUS_EXCEPTION(-120, null),
    /**
     * 客户端请求释放主动技能
     */
    P_CLIENT_SCENE_USE_SKILL(-121, null),
    /**
     * 通知客户端开始释放主动技能
     */
    P_SCENE_CLIENT_BEGIN_USE_SKILL(-122, null),
    /**
     * 知客户端开始执行一个action(广播)
     */
    P_SCENE_CLIENT_PERFORM_ACTION(-123, null),
    /**
     * 通知客户端冒字信息(血量变化信息)(广播)
     */
    P_SCENE_CLIENT_SHOW_DAMAGE_INFO(-124, null),
    /**
     * 通知客户端某一个游戏对象增加了一个buff(广播)
     */
    P_SCENE_CLIENT_ADD_NEW_BUFF(-125, null),
    /**
     * 移除某一个游戏对象的一个buff(广播)
     */
    P_SCENE_CLIENT_REMOVE_BUFF(-126, null),
    /**
     * 通知场景游戏对象退出技能
     */
    P_SCENE_CLIENT_NOTIFY_GAME_OBJECT_QUIT_SKILL(-127, null),
    /**
     * 同步游戏对象属性(暂定)
     */
    P_SCENE_CLIENT_SYNC_GAME_OBJECT_SPEED(-128, null),
    /**
     * 角色特殊移动速度同步(广播)
     */
    P_SCENE_CLIENT_SYNC_GAME_OBJECT_SPECIAL_SPEED(-129, null),
    /**
     * 某一个野怪死亡的消息(广播)
     */
    P_SCENE_CLIENT_NPC_DEAD(-130, null),
    /**
     * 通知客户端人物身上所有的任务信息
     */
    P_SCENE_CLIENT_SYNC_PLAYER_ALL_QUEST_INFO(-131, null),
    /**
     * 客户端请求提交任务
     */
    P_CLIENT_SCENE_SUBMIT_QUEST(-132, null),
    /**
     * 通知客户端提交任务成功
     */
    P_SCENE_CLIENT_SUBMIT_QUEST_SUCCESS(-133, null),
    /**
     * 通知客户端提交任务失败
     */
    P_SCENE_CLIENT_SUBMIT_QUEST_FAILED(-134, null),
    /**
     * 通知客户端单个任务目标完成次数更新
     */
    P_SCENE_CLIENT_SYNC_PLAYER_QUEST_TARGET_INFO(-135, null),
    /**
     * 客户端通知服务器玩家和某任务npc对话的信息
     */
    P_CLIENT_SCENE_QUEST_TALK_INFO(-136, null),
    /**
     * 服务器命令玩家进入对话
     */
    P_SCENE_CLIENT_COMMAND_PLAYER_TALK(-137, null),
    /**
     * 玩家请求领取章节奖励
     */
    P_CLIENT_SCENE_REQUEST_GAIN_MAIN_QUEST_CHAPTER_AWARD(-138, null),
    /**
     * 玩家成功领取章节奖励
     */
    P_SCENE_CLIENT_REQUEST_GAIN_MAIN_QUEST_CHAPTER_AWARD_SUCCESS(-139, null),
    /**
     * 返回领取章节奖励失败的原因
     */
    P_SCENE_CLIENT_REQUEST_GAIN_MAIN_QUEST_CHAPTER_AWARD_FAILED(-140, null),
    /**
     * 客户端通知服务器玩家移动到某一个region
     */
    P_CLIENT_SCENE_MOVE_INTO_REGION(-141, null),
    /**
     * 通知客户端接取了一个新任务
     */
    P_SCENE_CLIENT_ACCEPT_NEW_QUEST(-142, null),

    /**
     * 传图验证错误信息同步给客户端
     */
    P_SCENE_CLIENT_SWITCH_MAP_ERROR(-144, null),
    /**
     * 请求切线
     */
    P_CLIENT_SCENE_REQUEST_CHANGE_LINE(-145, null),
    /**
     * 请求切的线不存在
     */
    P_SCENE_CLIENT_REQUEST_CHANGE_LINE_NOT_EXIST(-146, null),
    //==================以下为背包相关协议=========================
    /**
     * 查询背包容器信息
     */
    P_CLIENT_SCENE_QUERY_BAG_INFO(-147, null),
    /**
     * 拖拽删除背包指定位置的物品
     */
    P_CLIENT_SCENE_DELETE_GOODS_FROM_BAG(-148, null),
    /**
     * 整理背包
     */
    P_CLIENT_SCENE_CLEAN_UP_BAG(-149, null),
    /**
     * 将装备从背包中装备到身上
     */
    P_CLIENT_SCENE_MOVE_GOODS_FROM_BAG_TO_BODY(-150, null),
    /**
     * 拆分包裹中指定位置的物品
     */
    P_CLIENT_SCENE_SPLIT_BAG_GOODS(-151, null),
    /**
     * 背包中移动物品
     */
    P_CLIENT_SCENE_MOVE_GOODS_IN_BAG(-152, null),
    /**
     * 将物品从背包中移动到仓库中
     */
    P_CLIENT_SCENE_MOVE_GOODS_FROM_BAG_TO_STORAGE(-153, null),
    /**
     * 双击使用包裹物品/单击批量使用包裹物品
     */
    P_CLIENT_SCENE_USE_BAG_GOODS(-154, null),
    /**
     * 通知客户背包容器信息
     */
    P_SCENE_CLIENT_SYNC_BAG_INFO(-155, null),
    /**
     * 通知客户端单个背包格子及物品信息
     */
    P_SCENE_CLIENT_SYNC_BAG_ITEM_INFO(-156, null),
    /**
     * 卖出背包物品
     */
    P_CLIENT_SCENE_SALE_BAG_GOODS(-157, null),
    /**
     * 查询回购商品列表
     */
    P_CLIENT_SCENE_QUERY_BUY_BACK_GOODS_INFO(-158, null),
    /**
     * 通知客户端回购卖出的物品
     */
    P_SCENE_CLIENT_BUY_BACK_GOODS_INFO(-159, null),
    /**
     * 玩家回购商品
     */
    P_CLIENT_SCENE_BUY_BACK_GOODS(-160, null),
    //================以上为背包相关协议==========================

    //================以下为仓库相关协议=======================
    /**
     * 查询仓库容器信息
     */
    P_CLIENT_SCENE_QUERY_STORAGE_INFO(-161, null),
    /**
     * 仓库中移动物品
     */
    P_CLIENT_SCENE_MOVE_GOODS_IN_STORAGE(-162, null),
    /**
     * 将物品从仓库中移动到背包中
     */
    P_CLIENT_SCENE_MOVE_GOODS_FROM_STORAGE_TO_BAG(-163, null),
    /**
     * 拖拽删除仓库指定位置的物品
     */
    P_CLIENT_SCENE_DELETE_GOODS_FROM_STORAGE(-164, null),
    /**
     * 整理仓库
     */
    P_CLIENT_SCENE_CLEAN_UP_STORAGE(-165, null),
    /**
     * 通知客户仓库容器信息
     */
    P_SCENE_CLIENT_SYNC_STORAGE_INFO(-166, null),
    /**
     * 通知客户端单个仓库格子及物品信息
     */
    P_SCENE_CLIENT_SYNC_STORAGE_ITEM_INFO(-167, null),
    //===============以上为仓库相关协议======================
    /**
     * 将装备从身上拖到背包中
     */
    P_CLIENT_SCENE_MOVE_GOODS_FROM_BODY_TO_BAG(-168, null),
    /**
     * 通知客户端物品操作失败
     */
    P_SCENE_CLIENT_GOODS_OPERATE_FAIL(-169, null),
    /**
     * 通知客户端获得奖励
     */
    P_SCENE_CLIENT_NOTIFY_GAIN_QUEST_AWARD_INFO(-170, null),
    /**
     * 通知客户端获得奖励
     */
    P_SCENE_CLIENT_GAIN_AWARD_FAILED(-171, null),
    /**
     * 通知玩家经验增加
     */
    P_SCENE_CLIENT_ADD_EXP(-172, null),
    /**
     * 通知玩家升级
     */
    P_SCENE_CLIENT_LEVEL_UP(-173, null),
    /**
     * 玩家从地上捡取Pack
     */
    P_CLIENT_SCENE_PICK_UP_PACK(-174, null),
    /**
     * 通知玩家拾取pack失败
     */
    P_SCENE_CLIENT_PICK_UP_PACK_FAILED(-175, null),
    /**
     * 通知客户端某个包裹的拾取权已经被取消
     */
    P_SCENE_CLIENT_REMOVE_PACK_OWNER_LIMIT(-176, null),
    /**
     * 购买背包商店物品
     */
    P_CLIENT_SCENE_BUY_IN_BAG_SHOP(-177, null),
    /**
     * 通知玩家购买背包商店失败
     */
    P_SCENE_CLIENT_BUY_BAG_SHOP_FAILED(-178, null),
    /**
     * 使用金钱扩展背包/仓库格子
     */
    P_CLIENT_SCENE_EXPAND_BAG_OR_STORAGE_GRID_USE_MONERY(-179, null),
    /**
     * 使用金钱扩展背包/仓库格子失败
     */
    P_SCENE_CLIENT_EXPAND_BAG_OR_STORAGE_GRID_FAILED(-180, null),
    /**
     * 背包/仓库格子扩展成功
     */
    P_SCENE_CLIENT_EXPAND_BAG_OR_STORAGE_GRID_SUCCESS(-181, null),
    /**
     * 请求服务器系统时间戳
     */
    P_CLIENT_SCENE_QUERY_SYS_SECOND_TIME(-182, null),
    /**
     * 和客户端同步服务器的系统时间戳
     */
    P_SCENE_CLIENT_SYS_SECOND_TIME(-183, null),
    /**
     * 和客户端同步玩家的游戏币信息
     */
    P_SCENE_CLIENT_SYS_GAMECOIN_INFO(-184, null),
    /**
     * 和客户端同步玩家的人民币信息
     */
    P_SCENE_CLIENT_SYS_RMBCOIN_INFO(-185, null),
    /**
     * 和客户端同步玩家的绑定人民币信息
     */
    P_SCENE_CLIENT_SYS_RMBCOINBINDED_INFO(-186, null),
    //--------------------------------坐骑系统begin--------------------
    /**
     * 客户端请求服务器坐骑面板信息
     */
    P_CLIENT_SCENE_QUERY_HORSE_PANEL_INFO(-187, null),
    /**
     * 和客户端同步坐骑面板信息
     */
    P_SCENE_CLIENT_SYS_HORSE_PANEL_INFO(-188, null),
    /**
     * 和客户端同步坐骑操作失败消息
     */
    P_SCENE_CLIENT_SYS_HORSE_OPERATION_FAIL(-189, null),
    /**
     * 客户端请求服务器使用坐骑资质丹
     */
    P_CLIENT_SCENE_USE_APRITUDE_DAN(-190, null),
    /**
     * 和客户端同步使用坐骑资质丹的总数量
     */
    P_SCENE_CLIENT_SYS_USE_HORSE_APRITUDE_DAN_NUM_INFO(-191, null),
    /**
     * 客户端请求改变自动购买材料状态
     */
    P_CLIENT_SCENE_CHANGE_HORSE_AUTO_BUY_GOODS_STATE(-192, null),
    /**
     * 客户端请求坐骑进阶
     */
    P_CLIENT_SCENE_HORSE_UPGRADE(-193, null),
    /**
     * 客户端请求改变消耗材料类型
     */
    P_CLIENT_SCENE_CHANGE_HORSE_UPGRADE_CONSUME_TYPE(-194, null),
    /**
     * 客户端请求坐骑幻化面板
     */
    P_CLIENT_SCENE_QUERY_HORSE_MAGIC_INFO(-195, null),
    /**
     * 和客户端同步坐骑幻化面板信息
     */
    P_SCENE_CLIENT_SYNC_HORSE_MAGIC_INFO(-196, null),
    /**
     * 客户端请求激活坐骑幻化皮肤
     */
    P_CLIENT_SCENE_ACTIVE_HORSE_MAGIC(-197, null),
    /**
     * 客户端请求设置骑乘的坐骑幻化皮肤
     */
    P_CLIENT_SCENE_CHANGE_RIDE_HORSE_MAGIC_ID(-198, null),
    /**
     * 和客户端同步当前骑乘的坐骑幻化皮肤id
     */
    P_SCENE_CLIENT_SYNC_HORSE_RIDE_HORSE_MAGICID(-199, null),
    //--------------------------------坐骑系统end--------------------
    //==========================聊天系统begin=========
    /**
     * 客户端请求发送聊天信息
     */
    P_CLIENT_SCENE_SEND_CHAT_CONTENT(-200, null),
    /**
     * 和客户端同步-玩家单一聊天-文本消息
     */
    P_SCENE_CLIENT_SINGLE_NOTIFY_TXT(-201, null),
    /**
     * 和客户端同步-玩家单一聊天-装备消息
     */
    P_SCENE_CLIENT_SINGLE_NOTIFY_EQUIP(-202, null),
    /**
     * 和客户端同步-玩家单一聊天-物品消息
     */
    P_SCENE_CLIENT_SINGLE_NOTIFY_GOODS(-203, null),
    /**
     * 和客户端同步-玩家单一聊天-位置消息
     */
    P_SCENE_CLIENT_SINGLE_NOTIFY_POSITION(-204, null),
    /**
     * 和客户端同步-玩家复合聊天-消息头
     */
    P_SCENE_CLIENT_MULTI_NOTIFY(-205, null),
    /**
     * 和客户端同步-玩家复合聊天-文本消息
     */
    P_SCENE_CLIENT_MULTI_NOTIFY_TXT(-206, null),
    /**
     * 和客户端同步-玩家复合聊天-装备消息
     */
    P_SCENE_CLIENT_MULTI_NOTIFY_EQUIP(-207, null),
    /**
     * 和客户端同步-玩家复合聊天-物品消息
     */
    P_SCENE_CLIENT_MULTI_NOTIFY_GOODS(-208, null),
    /**
     * 和客户端同步-玩家复合聊天-位置消息
     */
    P_SCENE_CLIENT_MULTI_NOTIFY_POSITION(-209, null),
    /**
     * 和客户端同步-系统消息-文本消息
     */
    P_SCENE_CLIENT_SYSTEM_NOTIFY_TXT(-210, null),
    /**
     * 和客户端同步聊天作失败消息
     */
    P_SCENE_CLIENT_SYS_CHAT_OPERATION_FAIL(-211, null),

    /**
     * 请求切换场景
     */
    P_SCENE_SM_REQUEST_CHANGE_TOWN(-212, null),

    /**
     * 要切换的场景不存在
     */
    P_SM_SCENE_CHANGE_SCENE_NOT_EXIST(-213, null),

    //-- 发送聊天信息
    P_SCENE_SM_SEND_CHAT(-214, null),
    P_SM_SCENE_SEND_CHAT(-215, null),
    //==========================聊天系统end=========
    //--查看玩家信息
    P_SCENE_SM_LOOK_PLAYER(-216, null),
    P_SM_SCENE_LOOK_PLAYER(-217, null),

    /**
     * 触发活动事件
     */
    P_RPCCLIENT_GLOBAL_SCHEDULE_ACTIVITY(-219, null),

    /**
     * 返回触发结果
     */
    P_RPCCLIENT_GLOBAL_SCHEDULE_ACTIVITY_RESULT(-220, null),

    /**
     * 触发活动事件
     */
    P_GLOBAL_SM_SCHEDULE_ACTIVITY(-221, null),

    /**
     * 触发活动事件
     */
    P_SM_SCENE_SCHEDULE_ACTIVITY(-222, null),

    /**
     * 重新读取策划数据
     */
    P_GLOBAL_SM_RELOAD_TEMPLATE(-223, null),
    /**
     * 重新读取策划数据
     */
    P_SM_SCENE_RELOAD_TEMPLATE(-224, null),

    /**
     * 重新读取策划数据
     */
    P_GLOBAL_AUTH_RELOAD_TEMPLATE(-225, null),
    /**
     * 触发重读策划数据事件
     */
    P_RPCCLIENT_GLOBAL_RELOAD_TEMPLATE(-226, null),

    // 跨进程向客户端提示错误信息
    P_SM_GLOBAL_PROTO_EXCEPTION(-227, null),
    P_GLOBAL_AUTH_PROTO_EXCEPTION(-228, null),

    // 活动事件相关
    P_SM_SCENE_ACTIVITY_ON(-229, null),
    P_SM_SCENE_ACTIVITY_OFF(-230, null),

    // 服务器内部属性
    P_SM_SCENE_SERVER_PROPERTIES(-231, null),
    P_SCENE_SM_SERVER_PROPERTIES(-232, null),

    /**
     * 触发重读数据库的服务器列表
     */
    P_RPCCLIENT_GLOBAL_RELOAD_SERVERLIST(-233, null),

    /**
     * 通过服id找到日志数据库的连接
     */
    P_RPCCLIENT_GLOBAL_GET_RECORDDBURL_BY_ACTUAL_SERVERID(-234, null),
    /**
     * GM 命令
     */
    P_PRCCLIENT_GLOBAL_GM_CMD(-235, null),
    /**
     * GM 命令
     */
    P_GLOBAL_SM_GM_CMD(-236, null),
    /**
     * sm重连的时候将自己连接到scene
     */
    P_SM_SCENE_RECONNECT(-237, null),
    /**
     * sm通知scene正在关闭中，可以发多个, 防止sm与scene断开后丢关服包
     */
    P_SM_SCENE_CLOSING(-238, null),

    /**
     * sm向global请求端口配置
     */
    P_RPCCLIENT_GLOBAL_QUERY_SM_PORT(-239,null),
    /**
     * scene向global请求内部端口配置
     */
    P_RPCCLIENT_GLOBAL_QUERY_SCENE_INNER_PORT(-240,null),
    P_RPCCLIENT_GLOBAL_QUERY_SCENE_OUTER_PORT(-241,null),

    /**
     * 获取订单号
     */
    P_RPCCLIENT_GLOBAL_GET_RECHARGEORDER(-242, null),

    /**
     * 充值回调
     */
    P_PRCCLIENT_GLOBAL_RECHARGE_CALLBACK(-243, null),

    /**
     * Global向指定sm给指定玩家充元宝
     */
    P_GLOBAL_SM_RECHARGE(-244, null),

    /**
     * 向Auth的 命令
     */
    P_PRCCLIENT_GLOBAL_AUTH_CMD(-245, null),

    /**
     * 向Auth的 命令
     */
    P_GLOBAL_AUTH_CMD(-246, null),

    /**
     * http向Global查询玩家是否在线
     */
    P_PRCCLIENT_CLIENT_GLOBAL_ISPLAYERONLINE(-247, null),

    /**
     * http向Global请求更新被禁ip表
     */
    P_PRCCLIENT_CLIENT_GLOBAL_RefuseIP(-248, null),

    /**
     * 触发向苹果推送
     */
    P_RPCCLIENT_GLOBAL_APPLE_NOTIFICATION(-250, null),

    /**
     * 将正在战斗玩家退出战斗
     */
    P_SM_SCENE_COMMAND_PLAYER_EXITBATTLE(-251, null),

    /**
     * 光宇通行证注册到游戏账号库中
     */
    P_HTTPCLIENT_REGISTER_GYYX_ACCOUNT(-252, null),

    ;
    private static final Int2ObjectLinkedOpenHashMap<ProtoEnum> protoEnums = new Int2ObjectLinkedOpenHashMap<>();

    private static final Map<Parser<?>, ProtoEnum> parsers = new HashMap<>();

    static {

        ProtoEnum[] values = ProtoEnum.values();

        for (ProtoEnum e : values) {
            if (protoEnums.containsKey(e.getValue())) {
                throw new IllegalArgumentException("重复的 ProtoEnum " + e.getValue());
            }
            protoEnums.put(e.getValue(), e);
            parsers.put(e.getParser(), e);
        }

    }

    private int value = 0;

    private Parser<?> parser = null;

    ProtoEnum(int value, Parser<?> parser) {
        this.value = value;
        this.parser = parser;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * 获取枚举参数值
     *
     * @return
     */
    public Parser<?> getParser() {
        return this.parser;
    }

    /**
     * 获取枚举对象
     *
     * @param value 消息id
     * @return 枚举对象
     */
    public static ProtoEnum getEnum(int value) {
        return protoEnums.get(value);
    }

    /**
     * 获取消息id
     *
     * @param parser 对应的parser
     * @return
     */
    public static ProtoEnum getMsgId(Parser<?> parser) {
        return parsers.get(parser);
    }
}
