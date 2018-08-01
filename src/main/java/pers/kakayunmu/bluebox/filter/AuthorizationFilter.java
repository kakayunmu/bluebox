package pers.kakayunmu.bluebox.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pers.kakayunmu.bluebox.entity.Member;
import pers.kakayunmu.bluebox.repositorys.MemberRepository;
import pers.kakayunmu.bluebox.util.GlobalParam;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class AuthorizationFilter implements Filter {

    @Autowired
    private GlobalParam globalParam;
    /**
     * 不需要过滤的 list 列表
     */
    protected static List<Pattern> patterns = new ArrayList<Pattern>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        patterns.add(Pattern.compile("api/login/.*"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }

        if (isInclude(url)) {
            chain.doFilter(httpRequest, httpResponse);
            return;
        } else {
            log.info("验证权限-url:" + url);
            String authorization = httpRequest.getHeader("Authorization");
            log.info("Authorization：" + authorization);
            if (needLogin(response, authorization)) return;
            Member mber = globalParam.get(authorization);
            if (mber == null) {
                if (needLogin(response, null))
                    return;
            }
            httpRequest.setAttribute("authorization", authorization);
            chain.doFilter(httpRequest, httpResponse);
            return;
        }
    }

    @Override
    public void destroy() {

    }

    private boolean needLogin(ServletResponse response, String authorization) throws IOException {
        if (null == authorization || "".equals(authorization)) {
            log.info("需要登录，已拦截");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print("{\"status\":\"-100\",\"msg\":\"请先登录\"}");
            return true;
        }
        return false;
    }

    /**
     * 是否需要过滤
     *
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
