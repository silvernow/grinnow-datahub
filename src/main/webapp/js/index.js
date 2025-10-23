$(function() {
    checkMongoStatus();

    $("#uploadBtn").on("click", function(e) {
	    e.preventDefault();
	
	    const file = $("#excelFile")[0].files[0];
	    if (!file) {
	        alert("📂 업로드할 엑셀 파일을 선택해주세요.");
	        return;
	    }
	
	    const formData = new FormData($("#uploadForm")[0]);
	
	    showOverlay("데이터 업로드 중...");
	
	    $.ajax({
	        url: "/main/uploadExcel",
	        type: "POST",
	        data: formData,
	        processData: false,
	        contentType: false,
	        xhr: function() {
	            const xhr = new XMLHttpRequest();
	            xhr.upload.addEventListener("progress", function(evt) {
	                if (evt.lengthComputable) {
	                    const percent = Math.round((evt.loaded / evt.total) * 100);
	                    updateProgress(percent, `업로드 중... ${percent}%`);
	                }
	            }, false);
	            return xhr;
	        },
	        dataType: "json",
	        success: function(res) {
	            if (res && res.result === "success") {
	                updateProgress(100, "✅ 업로드 완료!");
	                $("#pgCount").text(res.pgCount + " 건");
	                $("#mongoCount").text(res.mongoCount + " 건");
	                $("#mongoStatus").removeClass("offline").addClass("online")
	                    .html('<span class="dot"></span> 동기화 완료');
	            } else {
	                updateProgress(100, "⚠️ 업로드 실패");
	            }
	        },
	        error: function() {
	            updateProgress(100, "❌ 서버 통신 오류");
	        },
	        complete: function() {
	            setTimeout(hideOverlay, 1500);
	        }
	    });
	});

});

function showOverlay(text) {
    $("#overlay").css("display", "flex").hide().fadeIn(200);
    $("#progressBar").css("width", "0%").text("0%");
    $("#progressText").text(text || "잠시만 기다려주세요...");
}

function updateProgress(percent, text) {
    $("#progressBar").css("width", percent + "%").text(percent + "%");
    $("#progressText").text(text);
}

function hideOverlay() {
    $("#overlay").fadeOut(500, function() {
        $(this).css("display", "none");
    });
}

function checkMongoStatus() {
    $.ajax({
        url: "/main/checkMongo",
        type: "GET",
        success: function(res) {
            if (res.status === "online") {
                $("#mongoStatus").removeClass("offline").addClass("online")
                    .html('<span class="dot"></span> 연결됨');
            } else {
                $("#mongoStatus").removeClass("online").addClass("offline")
                    .html('<span class="dot"></span> 연결 실패');
            }
        },
        error: function() {
            $("#mongoStatus").removeClass("online").addClass("offline")
                .html('<span class="dot"></span> 연결 실패');
        }
    });
}