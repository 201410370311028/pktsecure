-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2018 at 09:23 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pktsecure`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `NPK` varchar(15) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` text NOT NULL,
  `Telepon` varchar(25) NOT NULL,
  `Role` tinyint(1) NOT NULL,
  `IsAktif` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`NPK`, `Username`, `Password`, `Telepon`, `Role`, `IsAktif`) VALUES
('201410370311028', 'rangga', '5f16b7574a0a4c3496731dd9398b3840', '08125521456', 3, 1),
('201410370311129', 'hidayat', '4148d5015ec83f35923748100cabaf43', '085350005549', 1, 1),
('201410370311130', 'rahman', 'e91229bfe8420a803d7db002a2dc1cb7', '081235015271', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `adminrole`
--

CREATE TABLE `adminrole` (
  `ID` smallint(6) DEFAULT NULL,
  `Role` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `adminrole`
--

INSERT INTO `adminrole` (`ID`, `Role`) VALUES
(1, 'Manager'),
(2, 'Superintandent'),
(3, 'Command Center');

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE `attachment` (
  `IDPengaduan` int(11) NOT NULL,
  `Caption` varchar(30) DEFAULT NULL,
  `Filepath` longtext,
  `Keterangan` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `globalsetting`
--

CREATE TABLE `globalsetting` (
  `Approval_1` varchar(50) DEFAULT NULL,
  `Approval_2` varchar(50) DEFAULT NULL,
  `Approval_3` varchar(50) DEFAULT NULL,
  `Jabatan_1` varchar(50) DEFAULT NULL,
  `Jabatan_2` varchar(50) DEFAULT NULL,
  `Jabatan_3` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `ID` int(11) NOT NULL,
  `action` varchar(20) DEFAULT NULL,
  `Timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CreatedBy` varchar(20) DEFAULT NULL,
  `Keterangan` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ms_contact_center`
--

CREATE TABLE `ms_contact_center` (
  `ID` int(11) NOT NULL,
  `Katagori` varchar(20) DEFAULT NULL,
  `SubKatagori` varchar(30) DEFAULT NULL,
  `Nomor` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ms_contact_center`
--

INSERT INTO `ms_contact_center` (`ID`, `Katagori`, `SubKatagori`, `Nomor`) VALUES
(0, 'Command Center', 'Dept. KAMTIB', '1234'),
(5, 'Pemadam Kebakaran', 'Pemadam Kebakaran Bontang', '(0548) 28113'),
(6, 'Pabrik', 'Falcon', '0707'),
(7, 'Pabrik', 'Pos Falcon', '0102'),
(8, 'Luar Pabrik', 'Pos Satpam Sintuk', '081357573434');

-- --------------------------------------------------------

--
-- Table structure for table `ms_jenisaduan`
--

CREATE TABLE `ms_jenisaduan` (
  `ID` smallint(6) NOT NULL,
  `Aduan` varchar(20) DEFAULT NULL,
  `Sorting` smallint(6) DEFAULT NULL,
  `Kategori` smallint(6) DEFAULT NULL,
  `Keterangan` smallint(6) DEFAULT NULL,
  `IsiAktif` tinyint(1) DEFAULT NULL,
  `CreatedBy` varchar(10) DEFAULT NULL,
  `CreatedOn` datetime DEFAULT NULL,
  `UpdatedBy` varchar(10) DEFAULT NULL,
  `UpdatedOn` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ms_panduan`
--

CREATE TABLE `ms_panduan` (
  `ID` int(11) NOT NULL,
  `Created` varchar(30) DEFAULT NULL,
  `Updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IsAktif` tinyint(1) DEFAULT NULL,
  `Judul` varchar(50) DEFAULT NULL,
  `Deskripsi` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ms_panduan`
--

INSERT INTO `ms_panduan` (`ID`, `Created`, `Updated`, `IsAktif`, `Judul`, `Deskripsi`) VALUES
(10, 'Hidayat', '2018-03-11 21:25:01', 1, 'Apa itu SISKA?', 'SISKA merupakan sebuah Sistem Keamanan Kambtib, yaitu aplikasi yang di rancang untuk mempermudah user untuk melaporkan segala hal yang terjadi yang berkaitan dengan keamanan ataupun ketertiban di lingkungan PT Pupuk Kalimantan Timur.');

-- --------------------------------------------------------

--
-- Table structure for table `panicbutton`
--

CREATE TABLE `panicbutton` (
  `Location` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pengaduan`
--

CREATE TABLE `pengaduan` (
  `ID` int(11) NOT NULL,
  `NamaPelapor` varchar(30) DEFAULT NULL,
  `NPKPelapor` varchar(10) DEFAULT NULL,
  `NoPengaduan` varchar(10) DEFAULT NULL,
  `Judul` varchar(100) DEFAULT NULL,
  `KategoriAduan` longtext,
  `JenisAduan` varchar(100) DEFAULT NULL,
  `Lokasi` varchar(30) DEFAULT NULL,
  `NoTelepon` varchar(20) DEFAULT NULL,
  `Waktu` datetime DEFAULT NULL,
  `DataFakta` varchar(100) DEFAULT NULL,
  `Kronologi` text,
  `Tindakan` varchar(100) DEFAULT NULL,
  `Uraian` longtext,
  `Status` varchar(15) DEFAULT NULL,
  `BUJP` char(3) NOT NULL,
  `Keterangan` varchar(255) DEFAULT NULL,
  `NPKPetugas` text NOT NULL,
  `isRead` int(2) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengaduan`
--

INSERT INTO `pengaduan` (`ID`, `NamaPelapor`, `NPKPelapor`, `NoPengaduan`, `Judul`, `KategoriAduan`, `JenisAduan`, `Lokasi`, `NoTelepon`, `Waktu`, `DataFakta`, `Kronologi`, `Tindakan`, `Uraian`, `Status`, `BUJP`, `Keterangan`, `NPKPetugas`, `isRead`) VALUES
(6, 'Rangga Aditya Julian Evirizal', '1101546743', 'KAMTIB/LAP', 'Hayuk Yukk', '1', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@b61ddde', 'DEPT TI', '08125521456', '2018-03-20 11:37:00', 'ryerureu', 'zsfsagsagd', 'pengujian', 'sasfsagasgsagasgasSafasfasgsagsg\nzdfasfasgas\ndfusdujsdjzu\nsdgdsayaejaejsr', 'On Proses', '', NULL, '', 0),
(7, 'Rangga Aditya Julian Evirizal', 'PKL', 'KAMTIB', 'Pengujian ', '1', '1', 'DEPT TI', '08125521456', '2018-03-20 11:37:00', 'Pengujian', 'Pengujian', 'pengujian', 'Pengujian', 'Close', '', NULL, '', 0),
(8, 'Rangga Aditya Julian Evirizal', 'PKL', 'KAMTIB', 'Pengujian ', '1', NULL, 'DEPT TI', '08125521456', '2018-03-20 11:37:00', NULL, NULL, 'pengujian', NULL, 'Verifikasi', '', NULL, '', 0),
(9, 'Bambang@gmail.com', '1101546743', 'LAP', 'sadasd', 'c', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@4b0586e', 'Jl. H.M. Ardans', '089845670987', '2018-03-25 08:48:36', 'asfsaf', 'safsafsaf', 'asdsadasd', 'asfsaf', 'On Proses', '1', NULL, '', 0),
(10, 'Bambang@gmail.com', '1101546743', 'LAP', 'sadsadsa', 'c', NULL, 'Jl. H.M. Ardans', '089845670987', '0000-00-00 00:00:00', NULL, NULL, 'sadasd', NULL, 'On Proses', '2', NULL, '', 0),
(11, 'Bambang@gmail.com', '1101546743', 'LAP', 'asdsad', 'c', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-03-25 08:50:41', NULL, NULL, 'adsadasd', NULL, 'On Proses', '', NULL, '', 0),
(12, 'Bambang@gmail.com', '1101546743', 'LAP', 'asdasda', 'com.example.rangg.pk', NULL, 'Jl. H.M. Ardans', '089845670987', '0000-00-00 00:00:00', NULL, NULL, 'sdasdasd', NULL, 'Verifikasi', '', NULL, '', 0),
(13, 'Bambang@gmail.com', '1101546743', 'LAP', 'awdwad', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@c4cff56', NULL, 'Jl. H.M. Ardans', '089845670987', '0000-00-00 00:00:00', NULL, NULL, 'adsad', NULL, 'Verifikasi', '', NULL, '', 0),
(14, 'Bambang@gmail.com', '1101546743', 'LAP', 'aaa', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@2c73862', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-03-25 08:52:26', NULL, NULL, 'aaa', NULL, 'On Proses', '', NULL, '', 0),
(15, 'Bambang@gmail.com', '1101546743', 'LAP', 'adasd', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@41c3b09', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-03-25 08:53:02', NULL, NULL, 'asdsad', NULL, 'On Proses', '', NULL, '', 0),
(16, 'Bambang@gmail.com', '1101546743', 'LAP', 'asfsaf', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@49aa834', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-03-25 09:06:12', NULL, NULL, 'asfsafsaf', NULL, 'On Proses', '', NULL, '', 0),
(17, 'Bambang@gmail.com', '1101546743', 'LAP', 'asdsad', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@6be2f77', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-03-25 09:07:12', NULL, NULL, 'asdsad', NULL, 'On Proses', '', NULL, '', 0),
(18, 'Bambang@gmail.com', '1101546743', 'LAP', 'asdsa', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@2d55d82', NULL, 'Jl. H.M. Ardans', '089845670987', '0000-00-00 00:00:00', NULL, NULL, 'asdsad', NULL, 'On Proses', '', NULL, '', 0),
(19, 'Bambang@gmail.com', '1101546743', 'LAP', 'sadsadsad', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@d57da4b', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-03-25 09:50:55', NULL, NULL, 'asdasdsad,asdsadsad', NULL, 'On Proses', '', NULL, '', 0),
(20, 'Bambang@gmail.com', '1101546743', 'LAP', 'saadasd', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@151a65d', NULL, 'Jl. H.M. Ardans', '089845670987', '0000-00-00 00:00:00', NULL, NULL, 'sdsadasd', NULL, 'On Proses', '', NULL, '', 0),
(21, 'Bambang@gmail.com', '1101546743', 'LAP', 'Pengujian 1nya', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@334577a', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@6ffb37e', 'Jl. H.M. Ardans', '089845670987', '2018-03-26 01:14:06', 'sdsad', 'asdsadasd', 'mmakan', 'sadsad', 'On Proses', '', NULL, '', 0),
(22, 'Bambang@gmail.com', '1101546743', 'LAP', 'Pengujian Hari Senin', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@ceb2bb6', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@15cdf6', 'Jl. H.M. Ardans', '089845670987', '2018-03-26 10:48:06', 'apa yah', 'dimana yah', 'Belum ada', 'kapan yah', 'Verifikasi', '', NULL, '', 0),
(23, 'Bambang@gmail.com', '1101546743', 'LAP', 'Kebakaran', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@9f7b747', NULL, 'Jl. H.M. Ardans', '089845670987', '0000-00-00 00:00:00', NULL, NULL, 'ihaifhas0ighia', NULL, 'On Proses', '', NULL, '', 0),
(24, 'Bambang@gmail.com', '1101546743', 'LAP', 'dfdfdfdf', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@e2eee24', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-04-24 21:07:34', NULL, NULL, 'rrrrrrr', NULL, 'On Proses', '', NULL, '', 0),
(25, 'Bambang@gmail.com', '1101546743', 'LAP', 'siapa', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@eaed045', NULL, 'Jl. H.M. Ardans', '089845670987', '0000-00-00 00:00:00', NULL, NULL, 'gdsdgds', NULL, 'On Proses', '', NULL, '', 0),
(26, 'Bambang@gmail.com', '1101546743', 'LAP', 'siapa', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@eaed045', NULL, 'Jl. H.M. Ardans', '089845670987', '0000-00-00 00:00:00', NULL, NULL, 'gdsdgds', NULL, 'On Proses', '', NULL, '', 0),
(27, 'Bambang@gmail.com', '1101546743', 'LAP', 'Chujay nakal', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@a6dc6ea', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@778126b', 'Jl. H.M. Ardans', '089845670987', '2018-04-02 04:30:42', 'Keabaran', 'oioio', 'di suntik', 'adafaf', 'On Proses', '', NULL, '', 0),
(28, 'Bambang@gmail.com', '1101546743', 'LAP', 'SIAPAKAN AKU', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@c704cd', NULL, 'Jl. H.M. Ardans', '089845670987', '0000-00-00 00:00:00', NULL, NULL, 'sasfsafsaf', NULL, 'Submitted', '', NULL, '', 0),
(29, 'Bambang@gmail.com', '1101546743', 'LAP', 'rangga', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@f30159', NULL, 'Jl. H.M. Ardans', '08125521456', '2018-05-05 11:27:27', NULL, NULL, 'apa ap', NULL, 'Submitted', '', NULL, '', 0),
(30, 'Bambang@gmail.com', '1101546743', 'LAP', 'Anjing gila', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@68d7ff9', NULL, 'Perum Bct', '08125521456', '2018-05-05 11:39:31', NULL, NULL, 'makan', NULL, 'Submitted', '', NULL, '', 0),
(31, 'Bambang@gmail.com', '1101546743', 'LAP', 'dasdasdasdas', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@7839520', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-05-05 11:49:40', NULL, NULL, 'adasdasdasdad', NULL, 'Submitted', '', NULL, '', 0),
(32, 'Bambang@gmail.com', '1101546743', 'LAP', 'asdfghqwerty', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@2331292', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-05-05 11:57:33', NULL, NULL, 'qwertyuiop', NULL, 'Submitted', '', NULL, '', 0),
(33, 'Bambang@gmail.com', '1101546743', 'LAP', 'asdfghjkl;\'#', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@42c001d', NULL, 'Jl. H.M. Ardans', '089845670987', '0000-00-00 00:00:00', NULL, NULL, 'zxcvbnm,', NULL, 'Submitted', '', NULL, '', 0),
(34, 'Bambang@gmail.com', '1101546743', 'LAP', 'asdfghjk,xcvbn', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@9ca92c7', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-05-05 12:08:47', NULL, NULL, 'qwertyuiol,mngfgtyhuikol', NULL, 'Submitted', '', NULL, '', 0),
(35, 'Bambang@gmail.com', '1101546743', 'LAP', 'zxcvbnm,', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@7c5b2eb', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-05-05 12:10:17', NULL, NULL, '12345678', NULL, 'Submitted', '', NULL, '', 0),
(36, 'Bambang@gmail.com', '1101546743', 'LAP', 'sdfgfdsdfgh', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@e87f63a', NULL, 'Jl. H.M. Ardans', '089845670987', '0000-00-00 00:00:00', NULL, NULL, 'zxcvbnmnbv', NULL, 'Submitted', '', NULL, '', 0),
(37, 'Bambang@gmail.com', '1101546743', 'LAP', 'poiuytr', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@d6bc5de', NULL, 'Jl. H.M. Ardans', '089845670987', '0000-00-00 00:00:00', NULL, NULL, 'xcvbnmtyui', NULL, 'Submitted', '', NULL, '', 0),
(38, 'Bambang@gmail.com', '1101546743', 'LAP', '1234', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@e87f63a', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-05-05 12:26:58', NULL, NULL, '4444443', NULL, 'Submitted', '', NULL, '', 0),
(39, 'Bambang@gmail.com', '1101546743', 'LAP', 'asdasd', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@2331292', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-05-05 13:20:26', NULL, NULL, 'asdasd', NULL, 'Submitted', '', NULL, '', 0),
(40, 'Bambang@gmail.com', '1101546743', 'LAP', '123123123', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@e539463', NULL, 'Jl. H.M. Ardans', '089845670987', '2018-05-05 13:27:17', NULL, NULL, 'qeqwewqewqwq', NULL, 'Submitted', '', NULL, '', 0),
(41, 'Bambang@gmail.com', '1101546743', 'LAP', 'terserah aja', 'com.example.rangg.pktsecure.Model.KategoriAduanModel@4e113bf', NULL, 'Jl. H.M. Ardans', '089845670987', '0000-00-00 00:00:00', NULL, NULL, 'lanjut next', NULL, 'Submitted', '', NULL, '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `pihakterlibat`
--

CREATE TABLE `pihakterlibat` (
  `IDPengaduan` int(11) NOT NULL,
  `Jenis` char(1) DEFAULT NULL,
  `Nama` varchar(30) DEFAULT NULL,
  `Alamat` varchar(50) DEFAULT NULL,
  `Pekerjaan` varchar(20) DEFAULT NULL,
  `Instansi` varchar(20) DEFAULT NULL,
  `TempatLahir` varchar(20) DEFAULT NULL,
  `TanggalLahir` date DEFAULT NULL,
  `Identitas` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `ID` smallint(6) DEFAULT NULL,
  `Role` varchar(10) DEFAULT NULL,
  `Permission` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`ID`, `Role`, `Permission`) VALUES
(1, 'KJS', 'Areal dalam pabrik'),
(2, 'PSB', 'Areal luar pabrik');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `NPK` varchar(15) NOT NULL,
  `NamaLengkap` longtext NOT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `Password` text,
  `Hubungan` smallint(1) NOT NULL,
  `Alamat` text,
  `Telepon` text,
  `IsAktif` tinyint(1) DEFAULT NULL,
  `Role` smallint(6) DEFAULT NULL,
  `UseLDAP` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`NPK`, `NamaLengkap`, `Username`, `Password`, `Hubungan`, `Alamat`, `Telepon`, `IsAktif`, `Role`, `UseLDAP`) VALUES
('1', '1', '1', '1', 0, '1', '1', 1, 5, 0),
('1101546632', '', 'supriadi@gmail.com', 'c4ca4238a0b923820dcc509a6f75849b', 0, 'Jl. Bhayangkara', '081235015271', 1, 2, NULL),
('1101546743', '', 'Bambang@gmail.com', 'c4ca4238a0b923820dcc509a6f75849b', 0, 'Jl. H.M. Ardans', '089845670987', 1, 5, NULL),
('1115756330', '', 'mukhlishadi', '731fe0ad012a1e2bced5cca3108b127f', 0, 'Jl. Brigjen Katamso', '085745678901', 0, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `verifikasi`
--

CREATE TABLE `verifikasi` (
  `ID` int(11) NOT NULL,
  `verifikasi` text,
  `Keterangan` text,
  `CreatedBy` varchar(20) DEFAULT NULL,
  `CreatedON` timestamp NULL DEFAULT NULL,
  `UpdateBy` varchar(20) DEFAULT NULL,
  `UpdateOn` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ClosedBy` varchar(20) DEFAULT NULL,
  `ClosedOn` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vl_hubungankeluarga`
--

CREATE TABLE `vl_hubungankeluarga` (
  `ID` smallint(6) NOT NULL,
  `hubungankeluarga` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vl_jenisaduan`
--

CREATE TABLE `vl_jenisaduan` (
  `ID` int(11) NOT NULL,
  `JenisAduan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vl_jenisaduan`
--

INSERT INTO `vl_jenisaduan` (`ID`, `JenisAduan`) VALUES
(1, 'Pencurian'),
(2, 'Penganiayaan'),
(3, 'Perjudian'),
(4, 'Perselingkuhan'),
(5, 'Kebakaran'),
(6, 'Demo/UnjuK Rasa'),
(7, 'Lalu Lintas'),
(8, 'Terorisme'),
(9, 'Pembunuhan'),
(10, 'Penyerobotan Lanah'),
(11, 'Penggelapan'),
(12, 'Pelanggaran Peraturan Perusahaan'),
(13, 'Penyalahgunaan Narkoba'),
(14, 'Pencemaran Nama Baik'),
(15, 'Ganguan Hewan Buas'),
(16, 'Lain-Lain');

-- --------------------------------------------------------

--
-- Table structure for table `vl_kategoriaduan`
--

CREATE TABLE `vl_kategoriaduan` (
  `ID` smallint(6) DEFAULT NULL,
  `Kategori` varchar(20) DEFAULT NULL,
  `Keterangan` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vl_kategoriaduan`
--

INSERT INTO `vl_kategoriaduan` (`ID`, `Kategori`, `Keterangan`) VALUES
(1, 'Kriminalitas', NULL),
(2, 'Pelanggaran', NULL),
(3, 'Lain - lain', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `vl_status`
--

CREATE TABLE `vl_status` (
  `ID` smallint(6) DEFAULT NULL,
  `Status` varchar(15) DEFAULT NULL,
  `Sorting` decimal(10,0) DEFAULT NULL,
  `Keterangan` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vl_status`
--

INSERT INTO `vl_status` (`ID`, `Status`, `Sorting`, `Keterangan`) VALUES
(1, 'Submited', '1', 'User menginputkan kasus'),
(2, 'On Process', '2', 'Kasus dalam tahap tindak lapangan'),
(3, 'Verifikasi', '3', 'Menunggu persetujuan manajer'),
(4, 'Revisi', '4', 'Manajer tidak menyetujui dan di evaluasi oleh superintendent'),
(5, 'Close', '5', 'Kasus telah di setujui oleh manajer dan kasus selesai');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`NPK`);

--
-- Indexes for table `attachment`
--
ALTER TABLE `attachment`
  ADD PRIMARY KEY (`IDPengaduan`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `ms_contact_center`
--
ALTER TABLE `ms_contact_center`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `ms_jenisaduan`
--
ALTER TABLE `ms_jenisaduan`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `ms_panduan`
--
ALTER TABLE `ms_panduan`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `pengaduan`
--
ALTER TABLE `pengaduan`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`NPK`);

--
-- Indexes for table `vl_hubungankeluarga`
--
ALTER TABLE `vl_hubungankeluarga`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `vl_jenisaduan`
--
ALTER TABLE `vl_jenisaduan`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ms_contact_center`
--
ALTER TABLE `ms_contact_center`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `ms_panduan`
--
ALTER TABLE `ms_panduan`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `pengaduan`
--
ALTER TABLE `pengaduan`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `vl_hubungankeluarga`
--
ALTER TABLE `vl_hubungankeluarga`
  MODIFY `ID` smallint(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vl_jenisaduan`
--
ALTER TABLE `vl_jenisaduan`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attachment`
--
ALTER TABLE `attachment`
  ADD CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`IDPengaduan`) REFERENCES `pengaduan` (`ID`);

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `history_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `pengaduan` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
