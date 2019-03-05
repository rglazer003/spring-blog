(function ($) {
    var request = $.ajax({'url': '/posts.json'});
    request.done(function (posts) {
        var html = '';
        posts.forEach(function (post) {
            html+='<div>';
            html+='<h1>'+post.title+'</h1>';
            html+='<p>'+post.post+'</p>';
            html+='<p>Posted by '+post.user.username+'</p>';
            html+='</div>';
        });
        $('#postsHold').html(html);
    });
})(jQuery);