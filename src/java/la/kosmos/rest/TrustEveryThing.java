package la.kosmos.rest;

import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;


public class TrustEveryThing implements X509TrustManager
{
	@Override
	public X509Certificate[] getAcceptedIssuers()
	{
		
		return null;
	}

	@Override
	public void checkClientTrusted(final X509Certificate[] certs, final String authType)
	{
		
	}

	@Override
	public void checkServerTrusted(final X509Certificate[] certs, final String authType)
	{
		
	}

}

