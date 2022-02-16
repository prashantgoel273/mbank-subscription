package in.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.demo.services.SubscriptionService;

@RestController
@RequestMapping(value = "/suscription")
public class SuscriptionController {

	@Autowired
	SubscriptionService subscriptionServ;

	@GetMapping(value = "/start")
	public String getWallentBalance(@RequestAttribute("user") String user, @RequestAttribute ("amt") double amt) {

		System.out.println("subscription strated "+user);
		return subscriptionServ.checkandActivateSubscription(user,amt);

	}


}
