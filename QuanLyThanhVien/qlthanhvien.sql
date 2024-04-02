-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 24, 2024 lúc 03:41 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Cơ sở dữ liệu: `qlthanhvien`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thanhvien`
--

CREATE TABLE `thanhvien` (
  `MaTV` int(10) AUTO_INCREMENT PRIMARY KEY,
  `HoTen` varchar(100) NOT NULL,
  `Khoa` varchar(100) DEFAULT NULL,
  `Nganh` varchar(100) DEFAULT NULL,
  `SDT` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--
-- Đang đổ dữ liệu cho bảng `thanhvien`
--

INSERT INTO `thanhvien` (`HoTen`, `Khoa`, `Nganh`, `SDT`) VALUES
('Trần Thị Nữ', 'GDTH', 'GDTH', 1111111111),
('Trần Thiếu Nam', 'TLH', 'QLGD', 1111111112),
('Ngô Tuyết Nhi', 'QTKD', 'QTKD', 1111111113),
('Nguyễn Văn Nam', 'CNTT', 'HTTT', 123456789);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thietbi`
--

CREATE TABLE `thietbi` (
  `MaTB` int(10) AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `TenTB` varchar(100) NOT NULL,
  `MoTaTB` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `thietbi`
--

INSERT INTO `thietbi` (`TenTB`, `MoTaTB`) VALUES
('Micro không dây MS2023', NULL),
('Micro không dây MS2024', NULL),
('Bảng điện tử trình chiếu', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongtinsd`
--

CREATE TABLE `thongtinsd` (
  `MaTT` int(10) AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `MaTV` int(10) NOT NULL,
  `MaTB` int(10) DEFAULT NULL,
  `TGVao` datetime DEFAULT NULL,
  `TGMuon` datetime DEFAULT NULL,
  `TGTra` datetime DEFAULT NULL,
  FOREIGN KEY (`MaTV`) REFERENCES `thanhvien` (`MaTV`),
  FOREIGN KEY (`MaTB`) REFERENCES `thietbi` (`MaTB`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `xuly`
--

CREATE TABLE `xuly` (
  `MaXL` int(10) AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `MaTV` int(10) NOT NULL,
  `HinhThucXL` varchar(250) DEFAULT NULL,
  `SoTien` int(100) DEFAULT NULL,
  `NgayXL` datetime DEFAULT NULL,
  `TrangThaiXL` int(2) DEFAULT NULL,
  FOREIGN KEY (`MaTV`) REFERENCES `thanhvien` (`MaTV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `xuly`
--

INSERT INTO `xuly` (`MaTV`, `HinhThucXL`, `SoTien`, `NgayXL`, `TrangThaiXL`) VALUES
(1, 'Khóa thẻ 1 tháng', NULL, '2023-09-12 08:00:00', 0),
(1, 'Khóa thẻ 2 tháng', NULL, '2023-09-12 08:00:00', 0),
(2, 'Bồi thường mất tài sản', 300000, '2023-09-12 08:00:00', 0);

COMMIT;
