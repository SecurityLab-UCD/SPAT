class n5808067 {
	@Override
	public void action(String msg, String uri, Gateway gateway) throws Exception {
		String city = "�ɶ�";
		if (msg.indexOf("#") != -1) {
			city = msg.substring(msg.indexOf("#") + 1);
		}
		String url = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather?theCityCode={city}&theUserID=";
		url = url.replace("{city}", URLEncoder.encode(city, "UTF8"));
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		if (200 == conn.getResponseCode()) {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(conn.getInputStream());
			List strings = doc.getRootElement().getChildren();
			String[] sugguestions = getText(strings.get(6)).split("\n");
			StringBuffer buffer = new StringBuffer();
			buffer.append("��ӭʹ��MapleSMS����������\n");
			buffer.append("���ѯ���� " + getText(strings.get(1)) + "��������\n");
			buffer.append(getText(strings.get(4)) + "��\n");
			buffer.append(getText(strings.get(5)) + "��\n");
			buffer.append(sugguestions[0] + "\n");
			buffer.append(sugguestions[1] + "\n");
			buffer.append(sugguestions[7] + "\n");
			buffer.append("��л��ʹ��MapleSMS����������ף����죡");
			gateway.sendSMS(uri, buffer.toString());
		} else {
			gateway.sendSMS(uri, "�Բ���������ĳ��и�ʽ�������������~");
		}
	}

}