-- Cấu trúc bảng cho bảng `thanhvien`
CREATE TABLE `thanhvien` (
  `MaTV` int(10) PRIMARY KEY,
  `HoTen` varchar(100) NOT NULL,
  `Khoa` varchar(100) DEFAULT NULL,
  `Nganh` varchar(100) DEFAULT NULL,
  `SDT` varchar(10) DEFAULT NULL,
  `Password` varchar(100) NOT NULL,
  `Email` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dữ liệu cho bảng `thanhvien`
INSERT INTO `thanhvien` (`MaTV`, `HoTen`, `Khoa`, `Nganh`, `SDT`, `Password`, `Email`) VALUES
(111941001, 'Trần Thị Nữ', 'GDTH', 'GDTH', '1111111111', 'password1', 'tran.thi.nu@example.com'),
(112042001, 'Trần Thiếu Nam', 'TLH', 'QLGD', '1111111112', 'password2', 'tran.thieu.nam@example.com'),
(112143001, 'Ngô Tuyết Nhi', 'QTKD', 'QTKD', '1111111113', 'password3', 'ngo.tuyet.nhi@example.com'),
(112241001, 'Nguyễn Văn Nam', 'CNTT', 'HTTT', '123456789', 'password4', 'nguyen.van.nam@example.com');

-- Cấu trúc bảng cho bảng `thietbi`
CREATE TABLE `thietbi` (
  `MaTB` int(10) AUTO_INCREMENT PRIMARY KEY,
  `TenTB` varchar(100) NOT NULL,
  `MoTaTB` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dữ liệu cho bảng `thietbi`
INSERT INTO `thietbi` (`TenTB`, `MoTaTB`) VALUES
('Micro không dây MS2023', NULL),
('Micro không dây MS2024', NULL),
('Bảng điện tử trình chiếu', NULL);

-- Cấu trúc bảng cho bảng `thongtinsd`
CREATE TABLE `thongtinsd` (
  `MaTT` int(10) AUTO_INCREMENT PRIMARY KEY,
  `MaTV` int(10) NOT NULL,
  `MaTB` int(10) DEFAULT NULL,
  `TGVao` datetime DEFAULT NULL,
  `TGMuon` datetime DEFAULT NULL,
  `TGTra` datetime DEFAULT NULL,
  `TGDatcho` datetime DEFAULT NULL,
  FOREIGN KEY (`MaTV`) REFERENCES `thanhvien` (`MaTV`),
  FOREIGN KEY (`MaTB`) REFERENCES `thietbi` (`MaTB`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Cấu trúc bảng cho bảng `xuly`
CREATE TABLE `xuly` (
  `MaXL` int(10) AUTO_INCREMENT PRIMARY KEY,
  `MaTV` int(10) NOT NULL,
  `HinhThucXL` varchar(250) DEFAULT NULL,
  `SoTien` int(100) DEFAULT NULL,
  `NgayXL` datetime DEFAULT NULL,
  `TrangThaiXL` int(2) DEFAULT NULL,
  FOREIGN KEY (`MaTV`) REFERENCES `thanhvien` (`MaTV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dữ liệu cho bảng `xuly`
INSERT INTO `xuly` (`MaTV`, `HinhThucXL`, `SoTien`, `NgayXL`, `TrangThaiXL`) VALUES
(111941001, 'Khóa thẻ 1 tháng', NULL, '2023-09-12 08:00:00', 0),
(111941001, 'Khóa thẻ 2 tháng', NULL, '2023-09-12 08:00:00', 0),
(112042001, 'Bồi thường mất tài sản', 300000, '2023-09-12 08:00:00', 0);
