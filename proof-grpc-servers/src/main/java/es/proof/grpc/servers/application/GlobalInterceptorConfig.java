package es.proof.grpc.servers.application;

import org.springframework.context.annotation.Configuration;

import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;

@Configuration(proxyBeanMethods = false)

public class GlobalInterceptorConfig {

	@GrpcGlobalServerInterceptor
	LogGrpcInterceptor logServerInterceptor() {
		return new LogGrpcInterceptor();
	}
}
