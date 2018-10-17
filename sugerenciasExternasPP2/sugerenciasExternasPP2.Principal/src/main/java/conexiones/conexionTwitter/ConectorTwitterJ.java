package conexiones.conexionTwitter;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import conexiones.Interfaz.InterfaceConexion;
import properties.Constants;
import properties.PropertiesPrincipal;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
 
public class ConectorTwitterJ implements InterfaceConexion
{
	
	public ConectorTwitterJ(){

	}
	
	@Override
	public void conectarse() {
		try {conectarConUsuarioDeTwitter();
		} catch (IOException | TwitterException e) {e.printStackTrace();}
	}

	
    public static void conectarConUsuarioDeTwitter() throws IOException, TwitterException
    {
    	//PrincipalProperties p= new PrincipalProperties();
    	PropertiesPrincipal pp= new PropertiesPrincipal(Constants.ROUTE_PROPERTIES);
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
        	.setOAuthConsumerKey(pp.leerValorDeUnaClave("ConsumerKey"))//(p.leerArchivo().ConsumerKey)
        	.setOAuthConsumerSecret(pp.leerValorDeUnaClave("ConsumerSecret"));//(p.leerArchivo().ConsumerSecret);
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        // Si están seteados el Token y el TokenSecret la siguiente
        // linea lanzará IllegalStateException
        RequestToken requestToken = twitter.getOAuthRequestToken();
        System.out.println("Obtenido request token.");
        System.out.println("Request token: " + requestToken.getToken());
        System.out.println("Request token secret: " + requestToken.getTokenSecret());
        AccessToken accessToken = null;
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (null == accessToken)
        {
            String osName = System.getProperty("os.name");
            String url = requestToken.getAuthorizationURL();
            System.out.println("La siguiente URL será abierta en su navegador:");
            System.out.println(requestToken.getAuthorizationURL());
            if (osName.contains("Windows"))
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            else
                if (osName.contains("Mac OS"))
                    Runtime.getRuntime().exec("open " + url);
                else
                    if (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0)
                    {
                        String[] browsers =
                        { "epiphany", "firefox", "mozilla", "konqueror", "netscape", "opera", "links", "lynx" };
                        StringBuffer cmd = new StringBuffer();
                        for (int i = 0; i < browsers.length; i++)
                            cmd.append((i == 0 ? "" : " || ") + browsers[i] + " \"" + url + "\" ");
 
                        Runtime.getRuntime().exec(new String[]
                        { "sh", "-c", cmd.toString() });
                    }
            System.out.print("Introduce el PIN y pulsa enter.\n[PIN]: ");
            String pin = br.readLine();
            if (pin.length() > 0)
                accessToken = twitter.getOAuthAccessToken(requestToken, pin);
            else
                // Si eres una aplicación de confianza (una multinacional por // ejemplo) no necesitas PIN, por eso aparece esta línea
                accessToken = twitter.getOAuthAccessToken(requestToken);
        }
        System.out.println("Obtenido el access token.");
        System.out.println("Access token: " + accessToken.getToken());
        System.out.println("Access token secret: " + accessToken.getTokenSecret());
 
        System.exit(0);
 
    }
    
    
    
    static { 
    	try {conectarConUsuarioDeTwitter();
		} catch (IOException | TwitterException e) {e.printStackTrace();}
    }
	
}
