-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3306
-- Thời gian đã tạo: Th1 04, 2021 lúc 03:29 AM
-- Phiên bản máy phục vụ: 5.7.31
-- Phiên bản PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `sinhvien1`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ketqua`
--

DROP TABLE IF EXISTS `ketqua`;
CREATE TABLE IF NOT EXISTS `ketqua` (
  `masv` varchar(50) NOT NULL,
  `makh` varchar(50) NOT NULL,
  `diem` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `ketqua`
--

INSERT INTO `ketqua` (`masv`, `makh`, `diem`) VALUES
('01T', 'KH1', 7.5),
('02T', 'KH2', 9.4),
('03T', 'KH3', 2.5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khoahoc`
--

DROP TABLE IF EXISTS `khoahoc`;
CREATE TABLE IF NOT EXISTS `khoahoc` (
  `makh` varchar(50) NOT NULL,
  `mamh` varchar(50) NOT NULL,
  `hocky` int(11) NOT NULL,
  `nam` int(11) NOT NULL,
  `giaovien` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `khoahoc`
--

INSERT INTO `khoahoc` (`makh`, `mamh`, `hocky`, `nam`, `giaovien`) VALUES
('KH1', 'MH01', 2, 2019, 'Mr.Thinh'),
('KH4', 'MH04', 3, 2020, 'Mr.Cuong'),
('KH5', 'MH05', 2, 2015, 'Mr.Tam'),
('KH2', 'MH03', 3, 2018, 'Miss.Thao');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `monhoc`
--

DROP TABLE IF EXISTS `monhoc`;
CREATE TABLE IF NOT EXISTS `monhoc` (
  `mamh` varchar(50) NOT NULL,
  `tenmh` varchar(50) NOT NULL,
  `tinchi` int(11) NOT NULL,
  `khoa` varchar(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `monhoc`
--

INSERT INTO `monhoc` (`mamh`, `tenmh`, `tinchi`, `khoa`) VALUES
('MH01', 'Tin Hoc', 3, 'IT'),
('MH02', 'Java', 3, 'CNTT'),
('MH03', 'Back-End', 3, 'IT'),
('MH04', 'C#', 5, 'CNTT'),
('MH05', 'PHP', 2, 'CNNT'),
('MH06', 'Font-End', 2, 'CNNT'),
('MH01', 'Tin Hoc', 3, 'IT');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sinhvien`
--

DROP TABLE IF EXISTS `sinhvien`;
CREATE TABLE IF NOT EXISTS `sinhvien` (
  `masv` varchar(50) NOT NULL,
  `hoten` varchar(50) NOT NULL,
  `namthu` int(11) NOT NULL,
  `khoa` varchar(11) NOT NULL,
  `gioitinh` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `sinhvien`
--

INSERT INTO `sinhvien` (`masv`, `hoten`, `namthu`, `khoa`, `gioitinh`) VALUES
('01T', 'Ngoc Tam', 2, 'Wed', 'Nam'),
('03T', 'Nguyen Thi My Huyen', 1, 'Kinh doanh', 'Nu'),
('02T', 'Thái dúi', 2, 'CNTT', 'Nu');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `taikhoan` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `soDT` int(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`taikhoan`, `password`, `soDT`) VALUES
('admin', '23032001', 939461842),
('admin2', '12345', 113),
('admin3', '01234', 115),
('Tam12', '0000', 115),
('Thaidui12', '1111', 0),
('Thaibulol', '000', 1221111111),
('Tubulol', 'ccc', 111);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
