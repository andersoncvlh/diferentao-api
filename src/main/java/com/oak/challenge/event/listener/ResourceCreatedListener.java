package com.oak.challenge.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oak.challenge.event.ResourceCreatedEvent;

@Component
public class ResourceCreatedListener implements ApplicationListener<ResourceCreatedEvent> {

		@Override
		public void onApplicationEvent(ResourceCreatedEvent event) {
			HttpServletResponse response = event.getResponse();
			Long codigo = event.getCode();

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(codigo).toUri();
			response.setHeader("Location", uri.toASCIIString());
		}
	
}
