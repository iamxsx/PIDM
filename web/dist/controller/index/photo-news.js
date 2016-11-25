/**
 * Created by clouder on 16-9-24.
 */
(function () {

    /*$.ajax({
        url:'/article/getPhototNews',
        success:function (articles) {
            //这里是后台返回的数据
            init(articles);
        },
        error:function () {
            alert('获取图片新闻失败');
        }

    });

    var init = function (articles) {
        new Vue({
            el:'#carousel-example-generic',
            data:{
                articles:articles
            }
        });
    }*/



    new Vue({
        el:'#my-carousel',
        data:{
            articles:[],
            url:'/article/getPhototNews'
        },
        ready:function () {
            this.getPhotoNews();
        },
        methods:{
            getPhotoNews:function () {
                this.$http.get(this.url)
                    .then(function (reponse) {
                        this.$set('articles',reponse.body);
                        var article  = reponse.body;
                        console.log(reponse.body[0]);
                    },function (reponse) {
                        console.log('获取失败')
                    });
            }
        }
    });

})();

