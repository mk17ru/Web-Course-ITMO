window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
};

window.commonAjax = function(data, func) {
    $.ajax({
        type: "POST",
        dataType: "json",
        data: data,
        success: function (response) {
            func(response);
            if (response["redirect"]) {
               // alert(response["redirect"]);
                location.href = response["redirect"];
            }
        }
    });
};
