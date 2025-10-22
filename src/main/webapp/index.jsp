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
			background: linear-gradient(135deg, #4caf50, #2e7d32);
			color: white;
			text-align: center;
			padding: 60px 20px;
			box-shadow: 0 4px 10px rgba(0,0,0,0.1);
		}
		header h1 {
			font-size: 2.2em;
			margin-bottom: 10px;
			letter-spacing: 1px;
		}
		header p {
			font-size: 1.1em;
			color: #e0f7e9;
			margin: 0;
		}
		main {
			max-width: 900px;
			margin: 50px auto;
			background: white;
			border-radius: 12px;
			padding: 40px 50px;
			box-shadow: 0 4px 20px rgba(0,0,0,0.08);
		}
		main h2 {
			color: #2e7d32;
			font-size: 1.5em;
			border-left: 6px solid #4caf50;
			padding-left: 10px;
			margin-bottom: 20px;
		}
		main p {
			line-height: 1.8;
			font-size: 1em;
			margin-bottom: 15px;
		}
		.section {
			margin-bottom: 40px;
		}
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

		#upload-section {
			text-align: center;
			margin-top: 40px;
		}
		#uploadResult {
			margin-top: 20px;
			padding: 15px;
			background-color: #f1f8e9;
			border-radius: 8px;
			display: none;
		}
		input[type="file"] {
			padding: 8px;
			border: 1px solid #ccc;
			border-radius: 5px;
			width: 60%;
			margin-top: 10px;
		}
	</style>
</head>

<body>
	<header>
		<h1>🌿 수목시세 수집서버</h1>
		<p>Tree Price Data Collection Server for Landscaping Intelligence</p>
	</header>
	<main>
		<form id="uploadForm" method="post" action="#a" enctype="multipart/form-data">
		<section class="section">
			<h2>시스템 개요</h2>
			<p>
				<b>수목시세 수집서버(Tree Price Collector)</b>는 전국 조경수 및 자재 시세 정보를 자동으로 수집하고,
				데이터베이스에 저장하여 시세 예측과 비교 분석에 활용하는 데이터 수집 플랫폼입니다.
			</p>
			<p>
				본 시스템은 엑셀 업로드 기능을 통해 수목 단가 데이터를 일괄 등록할 수 있으며,
				향후 실시간 웹 크롤링 및 API 연동 기능으로 확장될 수 있습니다.
			</p>
		</section>

		<section class="section">
			<h2>주요 기능</h2>
			<p>✅ 수목시세 엑셀 업로드 및 DB 저장</p>
			<p>✅ 데이터 검증 및 오류 로깅</p>
			<p>✅ 수집 이력 관리 및 통계 조회</p>
			<p>✅ 향후 API 기반 자동 수집 및 예측모델 연계 지원</p>
		</section>

		<section class="section">
			<h2>개발 환경</h2>
			<p>• Framework: Spring / JSP / MyBatis</p>
			<p>• DB: PostgreSQL</p>
			<p>• Language: Java 1.8 / HTML / CSS / JavaScript</p>
			<p>• Version Control: Git / GitHub</p>
		</section>
		
		<div id="upload-section">
			<input type="file" id="excelFile" accept=".xlsx,.xls">
			<br>
			<a id="uploadBtn" class="btn-start">📁 엑셀 업로드 시작하기</a>

			<div id="uploadResult"></div>
		</div>
		</form>
	</main>

	<footer>
		Copyright © 2025 Grinnow Data Systems. All rights reserved.
	</footer>
	<script>
	$(function() {
	    $("#uploadBtn").on("click", function(e) {
	        e.preventDefault();

	        var file = $("#excelFile")[0].files[0];
	        if (!file) {
	            alert("업로드할 엑셀 파일을 선택해주세요.");
	            return;
	        }

	        var formData = new FormData();
	        formData.append("excelFile", file);

	        $("#uploadResult").hide().text("📤 업로드 중입니다... 잠시만 기다려주세요.");

	        $.ajax({
	            url: "/main/uploadExcel",
	            type: "POST",
	            data: formData,
	            processData: false,
	            contentType: false,
	            dataType: "json",   // ✅ 응답을 JSON으로 강제
	            success: function(res) {
	                console.log(res);
	                if (res && res.result === "success") {
	                    $("#uploadResult")
	                        .css("background-color", "#e8f5e9")
	                        .text("✅ 업로드 성공! " + res.count + "건의 데이터가 처리되었습니다.")
	                        .fadeIn();
	                } else {
	                    $("#uploadResult")
	                        .css("background-color", "#ffebee")
	                        .text("⚠️ 업로드 실패: " + (res.message || "파일 형식을 확인해주세요."))
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
	</script>
</body>
</html>
