$(".login").show();
$(".mainpage").hide();

function insertRow(data) {
    let list = $(".listMain");
    list.empty();
    list.append('<div class="content1"> <div class="cell">Username</div> <div class="cell">Duration</div> <div class="cell mid">Record Time</div> <div class="cell">Download</div> </div>');
    let detail = data["detail"];
    for (let i = 0; i < detail.length; i++) {
       let id = detail[i]["id"];

       let row = "<div class='content'> <div class='cell username'>" + detail[i]["username"] + "</div>" +
              "<div class='cell duration'>" + detail[i]["duration"] + "</div>" +
              "<div class='cell mid'>" + new Date(detail[i]["recordTime"]).toString() + "</div>" +
              "<div class='cell'><button class='download' id='" + id + "'>↓</button></div></div>";

       list.append(row);
    }
    $("button.download").each(function() {
        $(this).on("click", () => {
            let id = this.id.toString();
            let username = $(this).parent().siblings('.username').text();
            console.log("Download on click with id: " + id);
            $.ajax({
                url: "video/download",
                method: "POST",
                data: {"id": id},
                xhrFields: {
                    responseType: 'blob'
                },
                success: function (data) {
                    const url = window.URL.createObjectURL(data);
                    const a = document.createElement('a');
                    a.style.display = 'none';
                    a.href = url;
                    let filename = `${username}_${id}.webm`
                    a.download = filename;
                    document.body.appendChild(a);
                    a.click();
                    window.URL.revokeObjectURL(url);
                    alert(`${filename} has started downloading!`);
                },
                error: function (data) {
                    alert("fail" + data);
                }
            });
        });
    });
}

$(".getList").click(()=> {
    $.ajax({
        dataType: "json",
        url: "video/getList",
        method: "GET",
        success: function (data) {
            insertRow(data);
        },
        error: function (data) {
            alert("fail" + data);
        }
    });
});

$(".loginSubmit").click(()=> {
    let uname = $(".username").val();
    let upass = $(".password").val();
    $.ajax({
        dataType: "json",
        url: "user/login",
        method: "POST",
        data: {"username":uname, "password":upass},
        success: function (data) {
            if (data["success"] && data["detail"]["userType"] === 1) {
                console.log("Admin Logged In");
                $(".login").hide();
                $(".mainpage").show();
            };
        },
        error: function (data) {
            alert("fail" + data);
        }
    });
});

$(".logoutSubmit").click(() => {
    $.ajax({
        url: "user/logout",
        method: "POST",
        success: function (data) {
            console.log("User Logged Out");
            $(".content").empty();
            $(".login").show();
            $(".mainpage").hide();
        },
        error: function (data) {
            alert("fail" + data);
        }
    });
});

