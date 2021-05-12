-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 12, 2021 at 10:54 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cuahangdongho`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `MaTK` varchar(10) NOT NULL,
  `TenAdmin` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `chitiethd`
--

CREATE TABLE `chitiethd` (
  `MaHD` varchar(10) NOT NULL,
  `MaSP` varchar(10) NOT NULL,
  `DonGia` float NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `ThanhTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitiethd`
--

INSERT INTO `chitiethd` (`MaHD`, `MaSP`, `DonGia`, `SoLuong`, `ThanhTien`) VALUES
('hd1', 'sp11', 2000, 2, 4000),
('hd1', 'sp12', 2000, 2, 4000),
('HD2', 'masp1', 3000, 2, 6000),
('HD4', 'masp1', 2000, 4, 8000),
('HD4', 'sp11', 3000, 2, 6000),
('HD4', 'sp12', 3000, 2, 6000),
('HD5', 'masp1', 2000, 1, 2000),
('HD6', 'sp11', 3000, 1, 3000);

-- --------------------------------------------------------

--
-- Table structure for table `chitietpn`
--

CREATE TABLE `chitietpn` (
  `MaPN` varchar(20) NOT NULL,
  `MaSP` varchar(20) NOT NULL,
  `DonGia` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `ThanhTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitietpn`
--

INSERT INTO `chitietpn` (`MaPN`, `MaSP`, `DonGia`, `SoLuong`, `ThanhTien`) VALUES
('PN1', 'sp12', 2000, 2, 4000),
('PN2', 'sp11', 3000, 1, 3000);

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` varchar(10) NOT NULL,
  `MaNV` varchar(10) NOT NULL,
  `MaKH` varchar(10) NOT NULL,
  `NgayLap` text NOT NULL,
  `TongTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `MaNV`, `MaKH`, `NgayLap`, `TongTien`) VALUES
('hd1', '311', 'KH2', '2021-02-10', 200000),
('HD2', '311', 'KH2', '2021-5-9', 0),
('HD3', '311', 'KH2', '2021-5-9', 4000),
('HD4', '311', 'KH2', '2021-05-10', 20000),
('HD5', '311', 'KH2', '2021-05-10', 2000),
('HD6', '311', 'KH2', '2021-05-10', 3000);

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` varchar(10) NOT NULL,
  `TenKH` text NOT NULL,
  `DiaChi` varchar(200) NOT NULL,
  `SDT` varchar(13) NOT NULL,
  `NgaySinh` date NOT NULL,
  `GioiTinh` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `TenKH`, `DiaChi`, `SDT`, `NgaySinh`, `GioiTinh`) VALUES
('KH2', 'khabc', '99adv', '0778', '2001-01-01', 'Nam');

-- --------------------------------------------------------

--
-- Table structure for table `loaisp`
--

CREATE TABLE `loaisp` (
  `MaLSP` varchar(10) NOT NULL,
  `TenLSP` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loaisp`
--

INSERT INTO `loaisp` (`MaLSP`, `TenLSP`) VALUES
('LSP1', 'ROLEX'),
('LSP2', 'CASIO');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNCC` varchar(20) NOT NULL,
  `TenNCC` text NOT NULL,
  `DiaChi` varchar(200) NOT NULL,
  `SDT` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`, `DiaChi`, `SDT`) VALUES
('ncc1', 'sanh', 'ádas', '893712'),
('ncc3', 'sanh', '88gf', '123456789');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` varchar(10) NOT NULL,
  `TenNV` text NOT NULL,
  `NgaySinh` date NOT NULL,
  `DiaChi` varchar(300) NOT NULL,
  `SDT` varchar(13) NOT NULL,
  `Gioitinh` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `TenNV`, `NgaySinh`, `DiaChi`, `SDT`, `Gioitinh`) VALUES
('123', 'sfgf', '2020-03-03', 'dfgs', '0123456789', 'Nữ'),
('311', 'Văn Triều', '2001-02-28', '99ADV', '0777', 'Nam'),
('NV1', 'ádhc', '1999-03-02', 'ád', '0124567899', 'Nam');

-- --------------------------------------------------------

--
-- Table structure for table `phanquyen`
--

CREATE TABLE `phanquyen` (
  `MaQuyen` varchar(10) NOT NULL,
  `TenQuyen` text NOT NULL,
  `ChiTietQuyen` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phanquyen`
--

INSERT INTO `phanquyen` (`MaQuyen`, `TenQuyen`, `ChiTietQuyen`) VALUES
('Q1', 'Admin', 'all'),
('Q2', 'Quản lý', 'cửa hàng + nhập hàng+sản phẩm+loại sp +hóa đơn+phiếu nhập+nhân viên+khách hàng+ncc'),
('Q3', 'Nhân viên', 'cửa hàng + nhập hàng+ thêm, sửa khách hàng,ncc');

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MaPN` varchar(20) NOT NULL,
  `MaNV` varchar(20) NOT NULL,
  `MaNCC` varchar(20) NOT NULL,
  `NgayLap` text NOT NULL,
  `TongTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`MaPN`, `MaNV`, `MaNCC`, `NgayLap`, `TongTien`) VALUES
('PN1', '123', 'ncc1', '2021-05-11', 2000),
('PN2', 'NV1', 'ncc1', '2021-05-12', 3000);

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` varchar(10) NOT NULL,
  `TenSP` text NOT NULL,
  `MaLoai` varchar(10) NOT NULL,
  `DonGia` float NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `HinhAnh` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`MaSP`, `TenSP`, `MaLoai`, `DonGia`, `SoLuong`, `HinhAnh`) VALUES
('masp1', 'rolex', 'lsp1', 2000, 1, 'casio2.png'),
('sp11', 'casio', 'lsp', 3000, 2, 'MDV106B-2AV_large.png'),
('sp12', 'casio', 'lsp2', 3000, 2, 'GA700DC-1A_large.png'),
('sp2', 'casio', 'lsp2', 3000, 2, 'casio2.png');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaTK` varchar(20) NOT NULL,
  `MatKhau` varchar(20) NOT NULL,
  `MaQuyen` varchar(10) NOT NULL,
  `MaNV` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`MaTK`, `MatKhau`, `MaQuyen`, `MaNV`) VALUES
('admin', 'admin', 'Q1', '311'),
('quanly1', '1234', 'Q2', 'NV1'),
('vantrieu', 'vantrieu', 'Q3', '311');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD KEY `MaTK` (`MaTK`);

--
-- Indexes for table `chitiethd`
--
ALTER TABLE `chitiethd`
  ADD KEY `MaHD` (`MaHD`),
  ADD KEY `MaSP` (`MaSP`);

--
-- Indexes for table `chitietpn`
--
ALTER TABLE `chitietpn`
  ADD KEY `MaSP` (`MaSP`),
  ADD KEY `MaPN` (`MaPN`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `MaKH` (`MaKH`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Indexes for table `loaisp`
--
ALTER TABLE `loaisp`
  ADD PRIMARY KEY (`MaLSP`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Indexes for table `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`MaQuyen`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPN`),
  ADD KEY `MaNCC` (`MaNCC`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaTK`),
  ADD KEY `MaQuyen` (`MaQuyen`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`MaTK`) REFERENCES `taikhoan` (`MaTK`);

--
-- Constraints for table `chitiethd`
--
ALTER TABLE `chitiethd`
  ADD CONSTRAINT `chitiethd_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`),
  ADD CONSTRAINT `chitiethd_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Constraints for table `chitietpn`
--
ALTER TABLE `chitietpn`
  ADD CONSTRAINT `chitietpn_ibfk_1` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`),
  ADD CONSTRAINT `chitietpn_ibfk_2` FOREIGN KEY (`MaPN`) REFERENCES `phieunhap` (`MaPN`);

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  ADD CONSTRAINT `hoadon_ibfk_3` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`),
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`MaQuyen`) REFERENCES `phanquyen` (`MaQuyen`),
  ADD CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
