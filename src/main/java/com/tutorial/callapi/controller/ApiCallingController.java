package com.tutorial.callapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.tutorial.demo.data.dto.CmdGetDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping (path={"/api/v1/apicalling"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiCallingController {
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@GetMapping(path = "/getCommande/{code}")
	public ResponseEntity<List<CmdGetDTO>> getCmd(@PathVariable(value = "code") String code) {
		StringBuilder sb = new StringBuilder("http://localhost:8080/api/v1/commandes/getCommande/");
		sb.append(code);
		//String sb = "http://localhost:8080/api/v1/commandes/getCommande/".concat(code);//.concat("\"");
		System.out.println(sb);
		List <CmdGetDTO> cmdGetDTO = webClientBuilder.build()
								.get()
								.uri(sb)
								//.uri("http://localhost:8080/api/v1/commandes/getCommande/PPE31014100113248_13032019_PGC")
								.retrieve()
								.bodyToMono(new ParameterizedTypeReference<List<CmdGetDTO>>() {})
								.block();
		
		return ResponseEntity.status(HttpStatus.OK).body(cmdGetDTO);
		
	}
	
	
	
}
