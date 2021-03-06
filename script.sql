USE [master]
GO
/****** Object:  Database [TDShirt]    Script Date: 08/27/2021 21:32:11 ******/
CREATE DATABASE [TDShirt] ON  PRIMARY 
( NAME = N'TDShirt', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TDShirt.mdf' , SIZE = 2048KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'TDShirt_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TDShirt_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [TDShirt] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TDShirt].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TDShirt] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [TDShirt] SET ANSI_NULLS OFF
GO
ALTER DATABASE [TDShirt] SET ANSI_PADDING OFF
GO
ALTER DATABASE [TDShirt] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [TDShirt] SET ARITHABORT OFF
GO
ALTER DATABASE [TDShirt] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [TDShirt] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [TDShirt] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [TDShirt] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [TDShirt] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [TDShirt] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [TDShirt] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [TDShirt] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [TDShirt] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [TDShirt] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [TDShirt] SET  DISABLE_BROKER
GO
ALTER DATABASE [TDShirt] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [TDShirt] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [TDShirt] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [TDShirt] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [TDShirt] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [TDShirt] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [TDShirt] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [TDShirt] SET  READ_WRITE
GO
ALTER DATABASE [TDShirt] SET RECOVERY SIMPLE
GO
ALTER DATABASE [TDShirt] SET  MULTI_USER
GO
ALTER DATABASE [TDShirt] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [TDShirt] SET DB_CHAINING OFF
GO
USE [TDShirt]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 08/27/2021 21:32:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Product](
	[ProductID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](100) NOT NULL,
	[Description] [varchar](max) NOT NULL,
	[IMG] [varchar](max) NOT NULL,
	[Price] [float] NOT NULL,
	[Availability] [int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 08/27/2021 21:32:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Customer](
	[CustomerID] [int] IDENTITY(1,1) NOT NULL,
	[Fullname] [varchar](100) NOT NULL,
	[Address] [varchar](200) NOT NULL,
	[Email] [varchar](100) NOT NULL,
	[Phone] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Admin]    Script Date: 08/27/2021 21:32:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Admin](
	[Username] [varchar](50) NOT NULL,
	[Password] [varchar](50) NOT NULL,
	[Fullname] [varchar](100) NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Status]    Script Date: 08/27/2021 21:32:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Status](
	[StatusID] [int] IDENTITY(1,1) NOT NULL,
	[Status] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[StatusID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Size]    Script Date: 08/27/2021 21:32:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Size](
	[SizeID] [int] IDENTITY(1,1) NOT NULL,
	[Size] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Size] PRIMARY KEY CLUSTERED 
(
	[SizeID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ProductSize]    Script Date: 08/27/2021 21:32:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductSize](
	[ProductID] [int] NOT NULL,
	[SizeID] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 08/27/2021 21:32:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[CustomerID] [int] NOT NULL,
	[StatusID] [int] NOT NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 08/27/2021 21:32:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[OrderID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[SizeID] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  ForeignKey [FK_ProductSize_Product]    Script Date: 08/27/2021 21:32:12 ******/
ALTER TABLE [dbo].[ProductSize]  WITH CHECK ADD  CONSTRAINT [FK_ProductSize_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[ProductSize] CHECK CONSTRAINT [FK_ProductSize_Product]
GO
/****** Object:  ForeignKey [FK_ProductSize_Size]    Script Date: 08/27/2021 21:32:12 ******/
ALTER TABLE [dbo].[ProductSize]  WITH CHECK ADD  CONSTRAINT [FK_ProductSize_Size] FOREIGN KEY([SizeID])
REFERENCES [dbo].[Size] ([SizeID])
GO
ALTER TABLE [dbo].[ProductSize] CHECK CONSTRAINT [FK_ProductSize_Size]
GO
/****** Object:  ForeignKey [FK_Order_Customer]    Script Date: 08/27/2021 21:32:12 ******/
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Customer] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customer] ([CustomerID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Customer]
GO
/****** Object:  ForeignKey [FK_Order_Status]    Script Date: 08/27/2021 21:32:12 ******/
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Status] FOREIGN KEY([StatusID])
REFERENCES [dbo].[Status] ([StatusID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Status]
GO
/****** Object:  ForeignKey [FK_OrderDetail_Order]    Script Date: 08/27/2021 21:32:12 ******/
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Order] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Order]
GO
/****** Object:  ForeignKey [FK_OrderDetail_Product]    Script Date: 08/27/2021 21:32:12 ******/
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Product]
GO
/****** Object:  ForeignKey [FK_OrderDetail_Size]    Script Date: 08/27/2021 21:32:12 ******/
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Size] FOREIGN KEY([SizeID])
REFERENCES [dbo].[Size] ([SizeID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Size]
GO
