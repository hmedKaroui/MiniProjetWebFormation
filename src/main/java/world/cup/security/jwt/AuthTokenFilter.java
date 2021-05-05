package world.cup.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import world.cup.service.UserDetailsImpl;
import world.cup.service.UserDetailsServiceImpl;

import static world.cup.utilities.Constants.AUTHORITIES_KEY;


public class AuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {

			String jwt = parseJwt(request);
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				String username = jwtUtils.getUserNameFromJwtToken(jwt);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				final JwtParser jwtParser = Jwts.parser().setSigningKey("isiidl");

				final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(jwt);

				final Claims claims = claimsJws.getBody();

				String  [] res = userDetails.getAuthorities().toString().split(";");
				String[] yourArray = Arrays.copyOfRange(res, 1, res.length);

				String tmp = yourArray[yourArray.length -1].replace("]", "");
				yourArray[yourArray.length -1] = tmp;

				final Collection<? extends GrantedAuthority> finalAuthorities =
						Arrays.stream(yourArray)
								.map(SimpleGrantedAuthority::new)
								.collect(Collectors.toList());
				System.out.print("*********************************************-----------------------------------***"+finalAuthorities.toString());
				//UserDetailsImpl x = (UserDetailsImpl) userDetails;

				//UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				//		userDetails, null, userDetails.getAuthorities());
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, finalAuthorities);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));



				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}
}
