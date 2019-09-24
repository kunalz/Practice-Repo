package com.example.springsecurity.rolehierarchy.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.annotation.Jsr250Voter;
import org.springframework.security.access.expression.method.ExpressionBasedPreInvocationAdvice;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.prepost.PreInvocationAuthorizationAdviceVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class RoleHierarchyConfig extends GlobalMethodSecurityConfiguration{

	@Override
	protected AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
		ExpressionBasedPreInvocationAdvice expressionAdvice = new ExpressionBasedPreInvocationAdvice();
		expressionAdvice.setExpressionHandler(getExpressionHandler());
		/*
		 * if (prePostEnabled()) { decisionVoters .add(new
		 * PreInvocationAuthorizationAdviceVoter(expressionAdvice)); } if
		 * (jsr250Enabled()) { decisionVoters.add(new Jsr250Voter()); }
		 * decisionVoters.add(new RoleVoter());
		 */
		decisionVoters.add(roleVoter());
		decisionVoters.add(new AuthenticatedVoter());
		return new AffirmativeBased(decisionVoters);
	}
	
	
	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl r = new RoleHierarchyImpl();
		r.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
		return r;
	}

	
	@Bean
	public RoleVoter roleVoter() {
		return new RoleHierarchyVoter(roleHierarchy());
	}
	
	
}
