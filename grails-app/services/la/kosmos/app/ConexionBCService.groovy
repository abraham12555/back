package la.kosmos.app

class ConexionBCService {

    def socketRequest(String ip, String puerto, String message) throws Exception {
        Integer port = Integer.parseInt(puerto)

        SocketAddress sockaddr = new InetSocketAddress(ip, port);
        Socket cliente = new Socket();
        cliente.connect(sockaddr, 10000);

        BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()))

        PrintWriter salida = new PrintWriter(cliente.getOutputStream(), Boolean.TRUE)
        salida.write(message)
        salida.println(message)
        cliente.shutdownOutput()
        String intlResponse = entrada.readLine()

        entrada.close()
        salida.close()
        cliente.close()

        intlResponse = intlResponse.trim()

        return intlResponse
    }
}