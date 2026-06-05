<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>수목시세 수집서버</title>
	<link rel="shortcut icon" href="/images/favicon-24x24.png">
	<link rel="stylesheet" type="text/css" href="/css/common.css">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="/js/index.js"></script>
</head>

<body>
	<header>
		<h1>🌿 수목시세 수집서버</h1>
		<p>Tree Price Data Collector & MongoDB Synchronizer</p>
	</header>
	<main>
		<section class="section">
			<h2>📦 시스템 개요</h2>
			<p><b>수목시세 수집서버(Tree Price Collector)</b>는 조경수 시세정보를 PostgreSQL과 MongoDB Atlas에 동시에 저장하여,
			안정적인 백업과 실시간 분석을 지원하는 데이터 수집 플랫폼입니다.</p>
		</section>

		<form id="uploadForm" method="post" action="#a" enctype="multipart/form-data">
			<section class="section" id="upload-section">
				<h2>📁 데이터 업로드</h2>
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
	<div id="overlay">
		<div id="progressBox">
	    	<div id="loadingIcon">🌿</div>
	        <h3>데이터 업로드 중...</h3>
	        <div class="progress">
	        	<div id="progressBar" class="progress-bar" style="width:0%">0%</div>
	        </div>
	        <div id="progressText">잠시만 기다려주세요...</div>
	    </div>
	</div>
	<footer>
		Copyright © 2025 Grinnow Data Systems. All rights reserved.
	</footer>
</body>
</html>
