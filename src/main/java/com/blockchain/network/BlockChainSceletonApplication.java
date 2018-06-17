package com.blockchain.network;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class BlockChainSceletonApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BlockChainSceletonApplication.class, args);
		ApplicationContext context = SpringApplication.run(BlockChainSceletonApplication.class,args);
		System.out.println("Contains blockController  "+context.
				containsBeanDefinition("blockController"));

	}
}
