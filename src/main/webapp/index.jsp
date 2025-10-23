<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>수목시세 수집서버</title>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<style>
		body {
			font-family: 'Noto Sans KR', sans-serif;
			background-color: #f7f9fc;
			margin: 0;
			padding: 0;
			color: #333;
		}
		header {
			background: linear-gradient(135deg, #43a047, #1b5e20);
			color: white;
			text-align: center;
			padding: 60px 20px;
			box-shadow: 0 4px 10px rgba(0,0,0,0.1);
		}
		header h1 { font-size: 2.2em; margin-bottom: 10px; }
		header p { color: #c8e6c9; }

		main {
			max-width: 950px;
			margin: 50px auto;
			background: white;
			border-radius: 12px;
			padding: 40px 50px;
			box-shadow: 0 4px 20px rgba(0,0,0,0.08);
		}
		h2 {
			color: #2e7d32;
			font-size: 1.4em;
			border-left: 6px solid #4caf50;
			padding-left: 10px;
			margin-bottom: 15px;
		}
		p { line-height: 1.7; }

		.section { margin-bottom: 40px; }
		footer {
			text-align: center;
			color: #888;
			font-size: 0.9em;
			padding: 20px;
			margin-top: 40px;
		}

		.btn-start {
			display: inline-block;
			margin-top: 20px;
			padding: 12px 25px;
			background-color: #4caf50;
			color: white;
			border-radius: 6px;
			text-decoration: none;
			font-weight: 600;
			transition: background-color 0.3s;
			cursor: pointer;
		}
		.btn-start:hover { background-color: #388e3c; }

		input[type="file"] {
			padding: 8px;
			border: 1px solid #ccc;
			border-radius: 5px;
			width: 60%;
			margin-top: 10px;
		}

		#upload-section { text-align: center; margin-top: 40px; }

		.status-card {
			display: flex;
			justify-content: space-between;
			background: #f1f8e9;
			border: 1px solid #c8e6c9;
			border-radius: 8px;
			padding: 15px 20px;
			margin-top: 15px;
			text-align: left;
		}
		.status-card h3 {
			margin: 0 0 5px;
			font-size: 1.1em;
			color: #1b5e20;
		}
		.status-card .count {
			font-size: 1.4em;
			font-weight: bold;
			color: #388e3c;
		}

		.db-status {
			display: inline-flex;
			align-items: center;
			gap: 6px;
			font-weight: 500;
			font-size: 0.95em;
		}
		.db-status .dot {
			width: 10px;
			height: 10px;
			border-radius: 50%;
		}
		.db-status.online .dot { background-color: #4caf50; }
		.db-status.offline .dot { background-color: #e53935; }

		#uploadResult {
			margin-top: 25px;
			padding: 15px;
			border-radius: 8px;
			display: none;
		}
		.spinner {
			display: inline-block;
			width: 22px;
			height: 22px;
			border: 3px solid #e0e0e0;
			border-top: 3px solid #43a047;
			border-radius: 50%;
			animation: spin 0.8s linear infinite;
			margin-right: 10px;
		}
		@keyframes spin { 100% { transform: rotate(360deg); } }
	</style>
</head>

<body>
	<header>
		<h1>🌿 수목시세 수집서버</h1>
		<p>Tree Price Data Collector & MongoDB Synchronizer</p>
	</header>

	<main>
		<section class="section">
			<h2>📦 시스템 개요</h2>
			<p><b>수목시세 수집서버(Tree Price Collector)</b>는 조경수 시세정보를 PostgreSQL과 MongoDB Atlas에 동시에 저장하여, 안정적인 백업과 실시간 분석을 지원합니다.</p>
		</section>

		<form id="uploadForm" method="post" action="#a" enctype="multipart/form-data">
			<section class="section" id="upload-section">
				<h2>📁 엑셀 업로드</h2>
				<input type="file" id="excelFile" name="excelFile" accept=".xlsx,.xls"><br>
				<a id="uploadBtn" class="btn-start">🚀 업로드 및 저장 시작</a>

				<div id="uploadResult"></div>
			</section>
		</form>

		<section class="section">
			<h2>🧩 DB 연결 상태</h2>
			<div class="status-card">
				<div>
					<h3>PostgreSQL</h3>
					<p class="db-status online"><span class="dot"></span> 연결됨</p>
				</div>
				<div class="count" id="pgCount">0 건</div>
			</div>
			<div class="status-card">
				<div>
					<h3>MongoDB Atlas</h3>
					<p id="mongoStatus" class="db-status offline"><span class="dot"></span> 연결 확인 중...</p>
				</div>
				<div class="count" id="mongoCount">0 건</div>
			</div>
		</section>
	</main>

	<footer>Copyright © 2025 Grinnow Data Systems. All rights reserved.</footer>

	<script>
	$(function() {
	    checkMongoStatus();

	    $("#uploadBtn").on("click", function(e) {
	        e.preventDefault();

	        var file = $("#excelFile")[0].files[0];
	        if (!file) {
	            alert("📂 업로드할 엑셀 파일을 선택해주세요.");
	            return;
	        }

	        var formData = new FormData($("#uploadForm")[0]);

	        $("#uploadResult").hide().html('<div class="spinner"></div> 업로드 중입니다...').fadeIn();

	        $.ajax({
	            url: "/main/uploadExcel",
	            type: "POST",
	            data: formData,
	            processData: false,
	            contentType: false,
	            dataType: "json",
	            success: function(res) {
	                if (res && res.result === "success") {
	                    $("#pgCount").text(res.pgCount + " 건");
	                    $("#mongoCount").text(res.mongoCount + " 건");
	                    $("#mongoStatus").removeClass("offline").addClass("online").html('<span class="dot"></span> 동기화 완료');

	                    $("#uploadResult")
	                        .css("background-color", "#e8f5e9")
	                        .html("✅ 업로드 성공! PostgreSQL: " + res.pgCount + "건, MongoDB: " + res.mongoCount + "건이 저장되었습니다.")
	                        .fadeIn();
	                } else {
	                    $("#uploadResult")
	                        .css("background-color", "#ffebee")
	                        .text("⚠️ 업로드 실패: " + (res.message || "서버 오류"))
	                        .fadeIn();
	                }
	            },
	            error: function() {
	                $("#uploadResult")
	                    .css("background-color", "#ffebee")
	                    .text("❌ 서버 통신 오류가 발생했습니다.")
	                    .fadeIn();
	            }
	        });
	    });
	});

	function checkMongoStatus() {
	    $.ajax({
	        url: "/main/checkMongo",
	        type: "GET",
	        success: function(res) {
	            if (res.status === "online") {
	                $("#mongoStatus").removeClass("offline").addClass("online").html('<span class="dot"></span> 연결됨');
	            } else {
	                $("#mongoStatus").removeClass("online").addClass("offline").html('<span class="dot"></span> 연결 실패');
	            }
	        },
	        error: function() {
	            $("#mongoStatus").removeClass("online").addClass("offline").html('<span class="dot"></span> 연결 실패');
	        }
	    });
	}
	</script>
</body>
</html>
