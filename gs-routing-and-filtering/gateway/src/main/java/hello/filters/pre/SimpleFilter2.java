package hello.filters.pre;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.common.io.CharStreams;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleFilter2 extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(SimpleFilter2.class);

//	@Autowired
//	private Book1 book1;
//	
//	@Autowired
//	private Book2 book2;
	
	@Autowired
	private Book3 book3;
	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 10;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		// InputStream stream =
		// RequestContext.getCurrentContext().getResponseDataStream();

		// List<String> lines;
		// try {
		// lines = IOUtils.readLines(stream);
		// System.out.println(lines);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		
		RequestContext ctx = RequestContext.getCurrentContext();

//		try (final InputStream responseDataStream = ctx.getResponseDataStream()) {
//			   final String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
//			   ctx.setResponseBody(responseData);
////			   System.out.println(responseData);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

//		System.out.println(book1.available());
		System.out.println(book3.available());
//		System.out.println(book2.checkedOut());

		ctx.setSendZuulResponse(false);
		
		return null;
	}

}
