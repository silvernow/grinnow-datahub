package grinnow.com.cmm.hndlr;

import java.io.IOException;
import java.io.Reader;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.taglibs.standard.tag.common.core.Util;

@SuppressWarnings("serial")
public class ComCrossSiteHndlr extends BodyTagSupport {

	protected Object value; // tag attribute
	protected String def; // tag attribute
	protected boolean escapeXml; // tag attribute
	private boolean needBody; // non-space body needed?

	private final String m_sDiffChar ="()[]{}\"',:;= \t\r\n%!+-";
	private final String m_sArrDiffChar [] = {
						"&#40;","&#41;",
						"&#91;","&#93;",
						"&#123;","&#125;",
						"&#34;","&#39;",
						"&#44;","&#58;",
						"&#59;","&#61;",
						" ","\t", //" ","\t",
						"\r","\n", //"\r","\n",
						"&#37;","&#33;",
						"&#43;","&#45;"
						};

	public ComCrossSiteHndlr() {
		super();
		init();
	}

	private void init() {
		value = def = null;
		escapeXml = true;
		needBody = false;
	}

	public void release() {
		super.release();
		init();
	}

	public int doStartTag() throws JspException {

		needBody = false; // reset state related to 'default'
		this.bodyContent = null; // clean-up body (just in case container is
									// pooling tag handlers)

		JspWriter out = pageContext.getOut();
		try {
			if (value != null) {
				String sWriteEscapedXml = getWriteEscapedXml();
				out.print(sWriteEscapedXml);
				return SKIP_BODY;
			} else {
				if (def == null) {
					needBody = true;
					return EVAL_BODY_BUFFERED;
				}
				if (def != null) {
					out(pageContext, escapeXml, def);
				}
				return SKIP_BODY;
			}
		} catch (IOException ex) {
			throw new JspException(ex.toString(), ex);
		}
	}

	public int doEndTag() throws JspException {
		try {
			if (!needBody){
				return EVAL_PAGE; // nothing more to do
			}

			if (bodyContent != null && bodyContent.getString() != null){
				out(pageContext, escapeXml, bodyContent.getString().trim());
			}
			return EVAL_PAGE;
		} catch (IOException ex) {
			throw new JspException(ex.toString(), ex);
		}
	}

	public static void out(PageContext pageContext, boolean escapeXml,
			Object obj) throws IOException {
		JspWriter w = pageContext.getOut();

		if (!escapeXml) {
			if (obj instanceof Reader) {
				Reader reader = (Reader) obj;
				char[] buf = new char[4096];
				int count;
				while ((count = reader.read(buf, 0, 4096)) != -1) {
					w.write(buf, 0, count);
				}
			} else {
				w.write(obj.toString());
			}
		} else {
			if (obj instanceof Reader) {
				Reader reader = (Reader) obj;
				char[] buf = new char[4096];
				int count;
				while ((count = reader.read(buf, 0, 4096)) != -1) {
					writeEscapedXml(buf, count, w);
				}
			} else {
				String text = obj.toString();
				writeEscapedXml(text.toCharArray(), text.length(), w);
			}
		}

	}
	public static void out2(PageContext pageContext, boolean escapeXml,	Object obj) throws IOException {
		JspWriter w = pageContext.getOut();
		w.write(obj.toString());
	}

	private static void writeEscapedXml(char[] buffer, int length, JspWriter w)
			throws IOException {
		int start = 0;

		for (int i = 0; i < length; i++) {
			char c = buffer[i];
			if (c <= Util.HIGHEST_SPECIAL) {
				char[] escaped = Util.specialCharactersRepresentation[c];
				if (escaped != null) {
					if (start < i) {
						w.write(buffer, start, i - start);
					}
					w.write(escaped);
					start = i + 1;
				}
			}
		}
		if (start < length) {
			w.write(buffer, start, length - start);
		}
	}

	@SuppressWarnings("unused")
	private String getWriteEscapedXml() throws IOException {
		String sRtn = "";

		Object obj = this.value;

		int start = 0;
		String text = obj.toString();

		int length = text.length();
		char[] buffer = text.toCharArray();
		boolean booleanDiff = false;
		char[] cDiffChar =  this.m_sDiffChar.toCharArray();

		for(int i = 0; i < length; i++) {
			char c = buffer[i];

			booleanDiff = false;

			for(int k = 0; k < cDiffChar.length; k++){
				if(c == cDiffChar[k]){
					sRtn = sRtn + m_sArrDiffChar[k];
					booleanDiff = true;
					continue;
				}
			}

			if(booleanDiff) continue;

			if (c <= Util.HIGHEST_SPECIAL) {
				char[] escaped = Util.specialCharactersRepresentation[c];
				if (escaped != null) {
					for (int j = 0; j < escaped.length; j++) {
						sRtn = sRtn + escaped[j];
					}
					start = i + 1;
				}else{
					sRtn = sRtn + c;
				}
			}else{
				sRtn = sRtn + c;
			}
		}

		return sRtn;
	}

	@SuppressWarnings("unused")
	private String getWriteEscapedXml(String sWriteString) throws IOException {

		String sRtn = "";

		Object obj = sWriteString;

		int start = 0;
		String text = obj.toString();

		int length = text.length();
		char[] buffer = text.toCharArray();
		boolean booleanDiff = false;
		char[] cDiffChar =  this.m_sDiffChar.toCharArray();

		for(int i = 0; i < length; i++) {
			char c = buffer[i];

			booleanDiff = false;

			for(int k = 0; k < cDiffChar.length; k++){
				if(c == cDiffChar[k]){
					sRtn = sRtn + m_sArrDiffChar[k];
					booleanDiff = true;
					continue;
				}
			}

			if(booleanDiff) continue;

			if (c <= Util.HIGHEST_SPECIAL) {
				char[] escaped = Util.specialCharactersRepresentation[c];
				if (escaped != null) {
					for (int j = 0; j < escaped.length; j++) {
						sRtn = sRtn + escaped[j];
					}
					start = i + 1;
				}else{
					sRtn = sRtn + c;
				}
			}else{
				sRtn = sRtn + c;
			}
		}
		return sRtn;
	}

    public void setValue(Object value) {
        this.value = value;
    }

    public void setDefault(String def) {
        this.def = def;
    }

    public void setEscapeXml(boolean escapeXml) {
        this.escapeXml = escapeXml;
    }
}


