package org.zyyd.base.util;

public class StringClear {
	
	
	public String clear(String str,String type){
		String clearString="";
		if(str!=null){
			clearString=str;
			if(str.contains("<head>")){
				clearString=str.replace(str.substring(str.indexOf("<head>"),(str.indexOf("</head>")+7)), "");
			}
			if(clearString.contains("<html>")){
				clearString=clearString.replace("<html>", "");
			}
			if(clearString.contains("</html>")){
				clearString=clearString.replace("</html>", "");
			}
			if(clearString.contains("<body lang=ZH-CN link=blue vlink=purple style='text-justify-trim:punctuation'>")){
				clearString=clearString.replace("<body lang=ZH-CN link=blue vlink=purple style='text-justify-trim:punctuation'>", "");
			}
			if(clearString.contains("</body>")){
				clearString=clearString.replace("</body>", "");
			}
//			if(type.equals("choice")){
//				clearString=clearString.replace("position:relative;", "");
//			}
			clearString=clearString.replace("position:relative;", "");
			clearString=clearString.replace("position: relative;", "");
			clearString=clearString.replace("position:&nbsp;relative;", "");
			clearString=clearString.replace("top:54.0pt", "top:11.0pt");
			clearString=clearString.replace("mso-bidi-font-size: 10.5pt", "font-size: 10.5pt");
		}
		return clearString;
	}
}
