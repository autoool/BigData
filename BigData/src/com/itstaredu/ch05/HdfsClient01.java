package com.itstaredu.ch05;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsClient01 {

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
//		 configuration.set("fs.defaultFS", "hdfs://192.168.243.129:9000");
		 configuration.set("fs.replication", "2");
		 configuration.set("fs.blocksize", "64m");
		// configuration.set("fs.hdfs.impl",
		// "org.apache.hadoop.hdfs.DistributedFileSystem");
		try {
			//user root 或者sam 都行
			FileSystem fSystem = FileSystem.get(new URI( "hdfs://192.168.243.129:9000"),configuration,"sam");
//			fSystem.mkdirs(new Path("/testfolder2"));
		
		
			fSystem.copyFromLocalFile(new Path("E:/Personal/BigData/data-source/test/words.txt"), new Path("/words.txt"));
//			第一个false参数表示不删除源文件，第4个true参数表示使用本地原文件系统
//			fSystem.copyToLocalFile(false,new Path("/a.txt"), new Path("E:/Personal/BigData/data-source/test01/aa.txt"),true);
			FileStatus[] listStatus = fSystem.listStatus(new Path("/"));
			for (FileStatus fileStatus : listStatus) {
				System.out.println(fileStatus);
			}
			fSystem.close();
			System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
