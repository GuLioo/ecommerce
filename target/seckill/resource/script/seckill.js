//存放json表示，用分包的形式表示使逻辑清晰
var seckill={
    //封装秒杀相关ajax的地址
    URL:{
        now:function () {
            return '/seckill/time/now';
        },
        exposer:function (seckillId) {
            return '/seckill/'+seckillId+'/exposer';
        },
        execution:function (seckillId,md5) {
            return '/seckill/'+seckillId+'/'+md5+'/execution'
        }
    },
    handleSeckillkill:function(seckillId,node){
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId),{},function (result) {
            //回调函数中执行交互流程
            if(result&&result['success']){
                var exposer=result['data'];
                if(exposer['exposed']){
                    //开启秒杀
                    //获取秒杀地址
                    var md5=exposer['md5'];
                    var killUrl=seckill.URL.execution(seckillId,md5);
                    console.log("killUrl:"+killUrl);
                    //click是一直绑定,one只绑定一次防止连续点击服务器接收很多请求
                    $('#killBtn').one('click',function () {
                        //执行秒杀请求的操作,this指#killBtn
                        //先禁用按钮
                        $(this).addClass('disabled');
                        //发送秒杀请求执行秒杀
                        $.post(killUrl,{},function (result) {
                            if(result&&result['success']){
                                var killResult=result['data'];
                                var state=killResult['state'];
                                var stateInfo=killResult['stateInfo'];
                                //显示秒杀结果
                                node.html('<span class="label label-success">'+stateInfo+'</span>');

                            }
                        });
                    });
                    node.show();
                }
                else {
                    //未开启秒杀
                    var now=exposer['now'];
                    var start=exposer['start'];
                    var end=exposer['end'];
                    //重新计算计时逻辑
                    seckill.countdown(seckillId,now,start,end);
                }
            }
            else {
                console.log('result:'+result);
            }
        });
    },
    //放在上层因为可能多个地方都验证
    //isNaN判断是否数字
    validatePhone:function(phone){
      if(phone && phone.length==11 && !isNaN(phone)){
          return true;
      }
      else
          return false;
    },
    countdown:function(seckillId,nowTime,startTime,endTime){
        var seckillBox=$('#seckill-box');
        //时间判断
        if(nowTime>endTime){
            //秒杀结束
            seckillBox.html('秒杀结束');
        }
        else if(nowTime<startTime){
            //秒杀未开始,计时事件绑定
            //加1s防止用户端计时后出现计时偏移
            var killTime=new Date(startTime+1000);
            //event作用即时间变化时做时间输出
            seckillBox.countdown(killTime,function (event){
                //时间格式
                var format=event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown',function () {
                //获取秒杀地址，控制显示逻辑，执行秒杀
                seckill.handleSeckillkill(seckillId,seckillBox);
            });
        }
        else {
            //秒杀开始
            seckill.handleSeckillkill(seckillId,seckillBox);
        }
    },
    //详情页秒杀逻辑
    detail:{
        //详情页初始化
        init:function (params) {
            //手机验证和登陆,计时交互
            //规划交互流程
            //无后端，所以将手机号放入cookie
            var killPhone=$.cookie('killPhone');
            //验证手机号，即用户的登陆操作
            if(!seckill.validatePhone(killPhone)){
                //绑定手机
                var killPhoneModal=$('#killPhoneModal');
                //显示弹出层
                killPhoneModal.modal({
                    //显示弹出层
                    show:true,
                    //禁止位置关闭
                    backdrop:'static',
                    //关闭键盘事件
                    keyboard:false
                });
                //绑定点击事件
                $('#killPhoneBtn').click(function () {
                    var inputPhone=$('#killPhoneKey').val();
                    console.log('inputPhone='+inputPhone);//ToDo
                    if(seckill.validatePhone(inputPhone)){
                        //电话写入cookie，保存时间7天，只在seckill下有效
                        $.cookie('killPhone',inputPhone,{expires:7,path:'/seckill'});
                        //刷新页面，重走init()
                        window.location.reload();
                    }
                    else {
                        //先隐藏，再放内容，再show，避免用户看到中间半成效果
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误</label>').show(300);
                    }
                });
            }
            //已经登陆，计时交互
            var seckillId=params['seckillId'];
            var startTime=params['startTime'];
            var endTime=params['endTime'];
            $.get(seckill.URL.now(),{},function (result) {
                if(result&&result['success']){
                    var nowTime=result['data'];
                    //计时时间判断
                    seckill.countdown(seckillId,nowTime,startTime,endTime);
                }
                else
                    console.log('result'+result);
            });
        }
    }
}