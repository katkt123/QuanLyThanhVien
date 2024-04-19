-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 16, 2024 lúc 03:52 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlythanhvien`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thanhvien`
--

CREATE TABLE `thanhvien` (
  `MaTV` int(10) NOT NULL,
  `HoTen` varchar(100) NOT NULL,
  `Khoa` varchar(100) DEFAULT NULL,
  `Nganh` varchar(100) DEFAULT NULL,
  `SDT` varchar(100) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `thanhvien`
--

INSERT INTO `thanhvien` (`MaTV`, `HoTen`, `Khoa`, `Nganh`, `SDT`, `Password`, `Email`) VALUES
(1120010007, 'Nguyễn Gia Mai', 'Toán UD', 'Toán', '0911200100', '1120010007', '1120010007@gmail.com'),
(1120020019, 'Chu Phúc Ngọc', 'SP KHTN', 'Lí', '0911200200', '1120020019', '11200200019@gmail.com'),
(1120090014, 'Nguyễn Thị Mỹ Ngân', 'SP KHXH', 'Văn', '0911200900', '1120090014', '11200900014@gmail.com'),
(1120090018, 'Trần Thị Anh Ngọc', 'SP KHXH', 'Văn', '0911200900', '1120090018', '11200900018@gmail.com'),
(1120380064, 'Nguyễn Ngọc Quỳnh Lực', 'Ngoại Ngữ', 'NNA', '0911203800', '1120380064', '1120380064@gmail.com'),
(1120411311, 'Lê Việt Nga', 'CNTT', 'CNTT', '0911204113', '1120411311', '11204110311@gmail.com'),
(1120480015, 'Trần Phạm Ngọc Ly', 'Toán UD', 'Toán', '0911204800', '1120480015', '1120480015@gmail.com'),
(1120480216, 'Nguyễn Trần Thái Ngọc', 'Toán UD', 'Toán', '0911204802', '1120480216', '11204800216@gmail.com'),
(1120480217, 'Trần Minh Phúc Ngọc', 'Toán UD', 'Toán', '0911204802', '1120480217', '11204800217@gmail.com'),
(1121020009, 'Bùi Đình Thái My', 'SP KHTN', 'Lí', '0911210200', '1121020009', '1121020009@gmail.com'),
(1121100003, 'Nguyễn Đắc Phương Linh', 'SP KHXH', 'Sử', '0911211000', '1121100003', '1121100003@gmail.com'),
(1121100012, 'Trương Hoài Nga', 'SP KHXH', 'Sử', '0911211000', '1121100012', '11211000012@gmail.com'),
(1121110001, 'Phạm Thị Lan Khôi', 'SP KHXH', 'Địa', '0911211100', '1121110001', '1121110001@gmail.com'),
(1121130012, 'Lê Ngọc lan', 'Ngoại Ngữ', 'Anh', '0911211300', '1121130012', '1121130012@gmail.com'),
(1122090010, 'Lê Xuân Nam', 'SP KHXH', 'Văn', '0911220900', '1122090010', '11220900010@gmail.com'),
(1122090013, 'Nguyễn Mỹ Ngân', 'SP KHXH', 'Văn', '0911220900', '1122090013', '11220900013@gmail.com'),
(1122090015, 'Nguyễn Thủy Triều Ngọc', 'SP KHXH', 'Văn', '0911220900', '1122090015', '11220900015@gmail.com'),
(1122530016, 'Lê Thục Ly', 'QLGD', 'TLH', '0911225300', '1122530016', '1122530016@gmail.com'),
(1122550008, 'Nguyễn Gia My', 'QTKD', 'QTKD', '0911225500', '1122550008', '1122550008@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thietbi`
--

CREATE TABLE `thietbi` (
  `MaTB` int(10) NOT NULL,
  `TenTB` varchar(100) NOT NULL,
  `MoTaTB` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `thietbi`
--

INSERT INTO `thietbi` (`MaTB`, `TenTB`, `MoTaTB`) VALUES
(120191, 'Micro không dây 01', 'JBL VM 2001'),
(120192, 'Micro có dây 01', 'MCCD 1001'),
(120203, 'Micro không dây 02', 'BCE 9001'),
(120214, 'Micro không dây 03', 'M3001'),
(120235, 'Micro không dây 03', 'BCE UGX12'),
(220191, 'Máy chiếu Panasonic', 'PNSN 031'),
(320201, 'Máy ảnh Fuji', 'FJ 20201'),
(420201, 'Cassette Sony', 'CN 20201'),
(420212, 'Cassette Sony', 'CN 20211'),
(420213, 'Cassette TQ', 'CSTQ 20211'),
(420224, 'Cassette Sony', 'CS 20221'),
(420236, 'Cassette Sony', 'CS 2023'),
(520221, 'Tivi LG', 'TVLG021'),
(520222, 'Tivi Samsung', 'TVSS20221'),
(620231, 'Quạt đứng', 'QD 20231'),
(620232, 'Quạt đứng', 'QD 20232'),
(620233, 'Quạt đứng', 'QD 20233'),
(620234, 'Quạt đứng', 'QD 20234');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongtinsd`
--

CREATE TABLE `thongtinsd` (
  `MaTT` int(10) NOT NULL,
  `MaTV` int(10) NOT NULL,
  `MaTB` int(10) DEFAULT NULL,
  `TGVao` datetime DEFAULT NULL,
  `TGMuon` datetime DEFAULT NULL,
  `TGTra` datetime DEFAULT NULL,
  `TGDatCho` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `thongtinsd`
--

INSERT INTO `thongtinsd` (`MaTT`, `MaTV`, `MaTB`, `TGVao`, `TGMuon`, `TGTra`, `TGDatCho`) VALUES
(6, 1121110001, NULL, '2024-04-04 10:39:00', NULL, NULL, NULL),
(7, 1121130012, NULL, '2024-04-04 10:39:00', NULL, NULL, NULL),
(8, 1121100003, NULL, '2024-04-04 10:40:00', NULL, NULL, NULL),
(9, 1121110001, NULL, '2024-04-05 08:39:00', NULL, NULL, NULL),
(10, 1121130012, NULL, '2024-04-05 08:50:00', NULL, NULL, NULL),
(11, 1121100003, NULL, '2024-04-05 09:55:00', NULL, NULL, NULL),
(12, 1121110001, 220191, '2024-03-05 08:50:00', '2024-03-05 12:50:00', '2024-03-05 12:50:00', NULL),
(13, 1122090010, 120203, '2024-03-05 09:50:00', '2024-03-05 12:53:00', '2024-03-05 12:53:00', NULL),
(14, 1121100012, 320201, '2024-03-07 11:00:00', '2024-03-07 13:00:00', '2024-03-07 13:00:00', NULL),
(15, 1122090013, 420201, '2024-03-08 10:00:00', '2024-03-08 16:00:00', '2024-03-08 16:00:00', NULL),
(16, 1120090014, 120214, '2024-03-09 09:00:00', '2024-03-09 11:00:00', '2024-03-09 11:00:00', NULL),
(17, 1122090015, 420212, '2024-03-10 13:00:00', '2024-03-10 13:30:00', '2024-03-10 13:30:00', NULL),
(18, 1120480216, 420213, '2024-03-12 09:11:00', '2024-03-12 11:12:00', '2024-03-12 11:12:00', NULL),
(19, 1120480217, 520221, '2024-03-12 09:17:00', NULL, NULL, NULL),
(20, 1122550008, 620231, '2024-03-13 12:13:00', NULL, NULL, NULL),
(21, 1121020009, 620232, '2024-03-14 09:10:00', NULL, NULL, NULL),
(22, 1120020019, 620233, '2024-03-15 09:17:00', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `xuly`
--

CREATE TABLE `xuly` (
  `MaXL` int(10) NOT NULL,
  `MaTV` int(10) NOT NULL,
  `HinhThucXL` varchar(250) DEFAULT NULL,
  `SoTien` int(100) DEFAULT NULL,
  `NgayXL` datetime DEFAULT NULL,
  `TrangThaiXL` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `xuly`
--

INSERT INTO `xuly` (`MaXL`, `MaTV`, `HinhThucXL`, `SoTien`, `NgayXL`, `TrangThaiXL`) VALUES
(10, 1121110001, 'Khóa thẻ 1 tháng', 0, '2024-02-01 10:00:00', 0),
(11, 1121130012, 'Khóa thẻ 1 tháng', 0, '2024-02-02 10:59:00', 0),
(12, 1121100003, 'Khóa thẻ 1 tháng', 0, '2024-02-03 10:58:00', 0),
(13, 1120380064, 'Khóa thẻ 2 tháng', 0, '2024-03-04 10:58:00', 1),
(14, 1121110001, 'Bồi thường', 300000, '2024-03-05 10:58:00', 0),
(15, 1121130012, 'Bồi thường', 200000, '2024-03-06 10:58:00', 0),
(16, 1121100003, 'Khóa thẻ 1 tháng và bồi thường', 100000, '2024-03-07 10:58:00', 0),
(18, 1122090010, 'Khóa thẻ 2 tháng', 0, '2024-03-09 10:58:00', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `thanhvien`
--
ALTER TABLE `thanhvien`
  ADD PRIMARY KEY (`MaTV`);

--
-- Chỉ mục cho bảng `thietbi`
--
ALTER TABLE `thietbi`
  ADD PRIMARY KEY (`MaTB`);

--
-- Chỉ mục cho bảng `thongtinsd`
--
ALTER TABLE `thongtinsd`
  ADD PRIMARY KEY (`MaTT`),
  ADD KEY `MaTV` (`MaTV`,`MaTB`),
  ADD KEY `MaTB` (`MaTB`);

--
-- Chỉ mục cho bảng `xuly`
--
ALTER TABLE `xuly`
  ADD PRIMARY KEY (`MaXL`),
  ADD KEY `MaTV` (`MaTV`),
  ADD KEY `MaTV_2` (`MaTV`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `thongtinsd`
--
ALTER TABLE `thongtinsd`
  ADD CONSTRAINT `thongtinsd_ibfk_1` FOREIGN KEY (`MaTV`) REFERENCES `thanhvien` (`MaTV`),
  ADD CONSTRAINT `thongtinsd_ibfk_2` FOREIGN KEY (`MaTB`) REFERENCES `thietbi` (`MaTB`);

--
-- Các ràng buộc cho bảng `xuly`
--
ALTER TABLE `xuly`
  ADD CONSTRAINT `xuly_ibfk_1` FOREIGN KEY (`MaTV`) REFERENCES `thanhvien` (`MaTV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
