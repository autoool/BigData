package com.itstaredu.ch06;

import java.net.URI;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theories;

public class HdfsClient02 {

	FileSystem fsystem;
	
	@Before
	public  void before() {
		Configuration configuration = new Configuration();
//		 configuration.set("fs.defaultFS", "hdfs://192.168.243.129:9000");
		 configuration.set("fs.replication", "2");
		 configuration.set("fs.blocksize", "64m");
		// configuration.set("fs.hdfs.impl",
		// "org.apache.hadoop.hdfs.DistributedFileSystem");
		try {
			fsystem = FileSystem.get(new URI( "hdfs://192.168.243.129:9000"),configuration,"sam");
//			fSystem.mkdirs(new Path("/testfolder2"));


			fsystem.copyFromLocalFile(new Path("E:/Personal/BigData/data-source/test/words.txt"), new Path("/words.txt"));
//			第一个false参数表示不删除源文件，第4个true参数表示使用本地原文件系统
			fsystem.copyToLocalFile(false,new Path("/a.txt"), new Path("E:/Personal/BigData/data-source/test01/aa.txt"),true);
		
			System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	@After
	public void After() throws Exception{
		fsystem.close();
	}
	
	@Test
	public void mkdir() throws Exception{
		fsystem.mkdirs(new Path("/newdir"));
	}
	
	@Test
	public void other() throws Exception{
//		fsystem.rename(new Path(""),new Path(""));
//		recursive  是否递归
//		fsystem.delete(new Path(""), true);
//		fsystem.deleteOnExit(new Path(""));
		
		RemoteIterator<LocatedFileStatus> status = fsystem.listFiles(new Path("/"), true);
		while (status.hasNext()) {
			LocatedFileStatus item = status.next();
			String info = "";
			info += "路径："+item.getPath()+"\n";
			info += "文件长度:"+item.getLen()+"\n";
			info += "块大小:"+item.getBlockSize()+"\n";
			info += "块信息："+Arrays.toString(item.getBlockLocations())+"\n";
			info += "副本数量："+item.getReplication()+"\n";
			System.out.println(info);
		}		
	}
	
	/**
	 * 判断文件还是文件夹
	 */
	@Test
	public void findHdfs() throws Exception{
		FileStatus[] listStatus = fsystem.listStatus(new Path("/"));
		for (FileStatus fileStatus : listStatus) {
//			fileStatus.isDirectory()
//			fileStatus.isFile()
			System.out.println(fileStatus);
		}
	}
	

}
