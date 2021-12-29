import Numeric --importacao para imprimir numero decimais com 2 casas

vendas :: Int -> Int
vendas 1 = 10
vendas 2 = 30
vendas 3 = 17
vendas 4 = 5
vendas 5 = 25
vendas 6 = 11
vendas 7 = 26
vendas 8 = 0
vendas 9 = 29
vendas 10 = 35
vendas 11 = 40
vendas 12 = 25

preco :: Float
preco = 20.0

--funcao para formatar numeros com 2 casas decimais
formatFloatN floatNum numOfDecimals = showFFloat (Just numOfDecimals) floatNum ""

------------------------------------------------------

-- 1) funcoes de calculo do relatorio

------------------------------------------------------

--mostra o valor total de vendas
totalVendas :: Int -> Int
totalVendas n
    |(n==1) = vendas n
    |otherwise = vendas n + totalVendas (n-1)

--mostra o mes com a maior venda
maiorVenda ::  Int -> Int -> Int
maiorVenda n maior
    |(n==1) = vendas maior
    |(vendas maior > vendas (n-1)) = maiorVenda (n-1) maior
    |otherwise = maiorVenda (n-1) (n-1)

--mostra o mes com a menor venda
menorVenda :: Int -> Int -> Int
menorVenda n menor
    |(n==1) = vendas menor
    |(vendas menor < vendas (n-1)) = menorVenda (n-1) menor
    |otherwise = menorVenda (n-1) (n-1)

--mostra quantos meses nao venderam nada
vendaZerada :: Int -> Int
vendaZerada n
    |(n==0) = 0
    |(vendas n == 0) = 1 + vendaZerada (n-1)
    |otherwise = vendaZerada (n-1)

--retorna o valor em reais de vendas no mes
valorVendasMes :: Int -> Float
valorVendasMes x = fromIntegral(x) * preco

--soma as vendas do mes
totalVendaMeses :: Int -> Float
totalVendaMeses n
    |(n==1) = valorVendasMes (vendas n)
    |otherwise = valorVendasMes (vendas n) + totalVendaMeses (n-1)

--------------------------------------------------

--NOVA FUNCAO

-- funcao auxiliar para calcular a desvio padrao
-- calcula a media aritmetica
mediaAritmetica :: Int -> Float
mediaAritmetica n = (totalVendaMeses n) / fromIntegral(n)

-- calcula a desvio padrao
variancia :: Int -> Int -> Float
variancia n totalMeses
    |(n==1) = ((valorVendasMes (vendas n) - mediaAritmetica n) ^ 2) / fromIntegral(totalMeses)
    |otherwise =  variancia (n-1) totalMeses + (((valorVendasMes (vendas n) - mediaAritmetica n) ^ 2) / fromIntegral(totalMeses))

desvioPadrao :: Int -> Int -> Float
desvioPadrao n totalMeses = sqrt (variancia n totalMeses)

---------------------------------------------------

-- 2) funcoes para formatar a impressao

---------------------------------------------------

--constante com o numero de colunas
quantidadeColunas :: Int
quantidadeColunas = 50

--coloca espacos em branco
colocarEspacos :: Int -> String
colocarEspacos n
    |(n==1) = " "
    |otherwise = " " ++ colocarEspacos (n-1) 

--valores das linhas
tabelaMeses :: Int -> String
tabelaMeses x
    |(x==0) = colocarEspacos ((div quantidadeColunas 2) - 2) ++ "TABELA DE VENDAS\n\
        \MES" ++ colocarEspacos ((div quantidadeColunas 2) - 2) ++ "QUANTIDADE" ++ colocarEspacos ((div quantidadeColunas 5) + 6) ++"R$"
    |(x==1) = "JANEIRO" ++ colocarEspacos ((div quantidadeColunas 2) - 2) ++ (show (vendas x)) ++ colocarEspacos ((div quantidadeColunas 2) - 7) ++ formatFloatN(valorVendasMes(vendas x)) 2
    |(x==2) = "FEVEIREIRO" ++ colocarEspacos ((div quantidadeColunas 2) - 5) ++ (show (vendas x)) ++ colocarEspacos ((div quantidadeColunas 2) - 7) ++ formatFloatN(valorVendasMes(vendas x)) 2
    |(x==3) = "MARCO" ++ colocarEspacos (div quantidadeColunas 2) ++ (show (vendas x)) ++ colocarEspacos ((div quantidadeColunas 2) - 7) ++ formatFloatN(valorVendasMes(vendas x)) 2
    |(x==4) = "ABRIL" ++ colocarEspacos (div quantidadeColunas 2) ++ (show (vendas x)) ++ colocarEspacos ((div quantidadeColunas 2) - 7) ++ formatFloatN(valorVendasMes(vendas x)) 2
    |(x==5) = "MAIO" ++ colocarEspacos ((div quantidadeColunas 2) + 1) ++ (show (vendas x)) ++ colocarEspacos ((div quantidadeColunas 2) - 7) ++ formatFloatN(valorVendasMes(vendas x)) 2
    |(x==6) = "JUNHO" ++ colocarEspacos (div quantidadeColunas 2) ++ (show (vendas x)) ++ colocarEspacos ((div quantidadeColunas 2) - 7) ++ formatFloatN(valorVendasMes(vendas x)) 2
    |(x==7) = "JULHO" ++ colocarEspacos (div quantidadeColunas 2) ++ (show (vendas x)) ++ colocarEspacos ((div quantidadeColunas 2) - 7) ++ formatFloatN(valorVendasMes(vendas x)) 2
    |(x==8) = "AGOSTO" ++ colocarEspacos ((div quantidadeColunas 2) - 1) ++ (show (vendas x)) ++ colocarEspacos ((div quantidadeColunas 2) - 7) ++ formatFloatN(valorVendasMes(vendas x)) 2
    |(x==9) = "SETEMBRO" ++ colocarEspacos ((div quantidadeColunas 2) - 3) ++ (show (vendas x)) ++ colocarEspacos ((div quantidadeColunas 2) - 7) ++ formatFloatN(valorVendasMes(vendas x)) 2
    |(x==10) = "OUTUBRO" ++ colocarEspacos ((div quantidadeColunas 2) - 2) ++ (show (vendas x)) ++ colocarEspacos ((div quantidadeColunas 2) - 7) ++ formatFloatN(valorVendasMes(vendas x)) 2
    |(x==11) = "NOVEMBRO" ++ colocarEspacos ((div quantidadeColunas 2) - 3) ++ (show (vendas x)) ++ colocarEspacos ((div quantidadeColunas 2) - 7) ++ formatFloatN(valorVendasMes(vendas x)) 2
    |(x==12) = "DEZEMBRO" ++ colocarEspacos ((div quantidadeColunas 2) - 3) ++ (show (vendas x)) ++ colocarEspacos ((div quantidadeColunas 2) - 7) ++ formatFloatN(valorVendasMes(vendas x)) 2

---------------------------------------------------

-- 3) funcoes para imprimir a tabela

---------------------------------------------------

--imprime a tabela
imprimeTabelaMeses :: Int -> Int -> IO ()
imprimeTabelaMeses n totalMeses
    |(n==totalMeses+1) = return ()
    |otherwise = do
        putStrLn (tabelaMeses n)
        imprimeTabelaMeses (n+1) totalMeses

--tabela dos resultados
tabelaResultados :: Int -> Int -> String
tabelaResultados x totalMeses
    |(x==0) = "\nTOTAL" ++ colocarEspacos ((div quantidadeColunas 2) - 1) ++ (show (totalVendas totalMeses)) ++ colocarEspacos ((div quantidadeColunas 2) - 8) ++ formatFloatN(totalVendaMeses totalMeses) 2
    |(x==1) = "MAIOR" ++ colocarEspacos (div quantidadeColunas 2) ++ (show (maiorVenda totalMeses totalMeses))
    |(x==2) = "MENOR" ++ colocarEspacos (div quantidadeColunas 2) ++ (show (menorVenda totalMeses totalMeses))
    |(x==3) = "VENDA ZERADA" ++ colocarEspacos ((div quantidadeColunas 2) - 6) ++ (show (vendaZerada totalMeses))
    |(x==4) = "DESVIO PADRAO" ++ colocarEspacos (quantidadeColunas-13) ++ (formatFloatN(desvioPadrao totalMeses totalMeses) 2) 
    |(x==5) = "\nPREÇO DO PRODUTO" ++ colocarEspacos ((div quantidadeColunas 5) - 2) ++ "R$ " ++ (formatFloatN(preco) 2)

--imprime a tabela resultados
imprimeTabelaResultados :: Int -> Int -> IO ()
imprimeTabelaResultados n totalMeses
    |(n==6) = return ()
    |otherwise = do
        putStrLn (tabelaResultados n totalMeses)
        imprimeTabelaResultados (n+1) totalMeses

--imprime relatorio
relatorio :: Int -> IO ()
relatorio totalMeses |(totalMeses<=0) = putStrLn "Mês não existente! Digite um valor de 1 a 12."
relatorio totalMeses = do
    imprimeTabelaMeses 0 totalMeses
    imprimeTabelaResultados 0 totalMeses

main = do
   relatorio 5