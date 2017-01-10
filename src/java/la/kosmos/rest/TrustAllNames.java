package la.kosmos.rest;

import java.io.IOException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.conn.ssl.X509HostnameVerifier;

public class TrustAllNames implements X509HostnameVerifier
{
	@Override
	public void verify(final String string, final SSLSocket ssls) throws IOException
	{
	}

	@Override
	public void verify(final String string, final X509Certificate xc) throws SSLException
	{
	}

	@Override
	public void verify(final String string, final String[] strings, final String[] strings1) throws SSLException
	{
	}

	@Override
	public boolean verify(final String string, final SSLSession ssls)
	{
		return true;
	}
}