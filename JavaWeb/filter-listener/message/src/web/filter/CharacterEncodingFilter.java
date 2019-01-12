package web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter {

	private String encoding;
	private Boolean forceEncoding = false;//强制使用设置编码
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
		forceEncoding = Boolean.valueOf(config.getInitParameter("force"));
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//把请求和响应强制转化为HTTP协议
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if(hasLength(encoding) && (req.getCharacterEncoding() == null || forceEncoding)) {
			req.setCharacterEncoding(encoding);
		}
		
		chain.doFilter(req, resp);//放行操作
	}
	
	
	private boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}
	public void destroy() {
	}
}
