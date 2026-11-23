# Compila o plugin MapMeel v3 e copia o jar para a pasta de plugins do servidor.
# Uso: .\build.cmd  (ou: powershell -ExecutionPolicy Bypass -File .\build.ps1)
# Compativel com Windows PowerShell 5.1. Mantenha este arquivo em ASCII.

param(
    [string]$JdkBin = "F:\Organizar\servers\JDK\8\bin",
    [string]$ServerDir = "F:\Organizar\servers\MMv3",
    [string]$JarName = "MapMeel v3.jar",
    [switch]$NoCopy
)

$ErrorActionPreference = "Stop"

$root = $PSScriptRoot
$pluginsDir = Join-Path $ServerDir "plugins"
$buildDir = Join-Path $root "build"
$classesDir = Join-Path $buildDir "classes"
$jarOut = Join-Path $buildDir $JarName
$javac = Join-Path $JdkBin "javac.exe"
$jarExe = Join-Path $JdkBin "jar.exe"

function Stop-Build([string]$message) {
    Write-Host ""
    Write-Host "ERRO: $message" -ForegroundColor Red
    exit 1
}

# --- Verificacoes ---
foreach ($tool in @($javac, $jarExe)) {
    if (-not (Test-Path $tool)) { Stop-Build "Nao encontrei $tool (ajuste -JdkBin)." }
}
if (-not (Test-Path $pluginsDir)) { Stop-Build "Pasta de plugins nao existe: $pluginsDir" }

$libs = @(
    (Join-Path $ServerDir "spigot-1.10.2.jar"),
    (Join-Path $pluginsDir "Citizens-1.10.jar"),
    (Join-Path $pluginsDir "HologramAPI.jar"),
    (Join-Path $pluginsDir "worldedit-bukkit-6.1.3.jar")
)
foreach ($lib in $libs) {
    if (-not (Test-Path $lib)) { Stop-Build "Dependencia nao encontrada: $lib" }
}
$classpath = $libs -join ";"

# --- Limpa build anterior ---
if (Test-Path $buildDir) { Remove-Item -Recurse -Force $buildDir -Confirm:$false }
New-Item -ItemType Directory -Force $classesDir | Out-Null

# --- Lista de fontes (arquivo de argumentos em ASCII; javac nao le UTF-16) ---
$sources = Get-ChildItem -Path (Join-Path $root "src") -Recurse -Filter *.java | ForEach-Object { '"' + ($_.FullName -replace '\\', '/') + '"' }
if ($sources.Count -eq 0) { Stop-Build "Nenhum arquivo .java encontrado em src." }
$argFile = Join-Path $buildDir "sources.txt"
[System.IO.File]::WriteAllLines($argFile, [string[]]$sources, [System.Text.Encoding]::ASCII)

# --- Compila ---
Write-Host "Compilando $($sources.Count) arquivos..." -ForegroundColor Cyan
& $javac -nowarn -encoding Cp1252 -source 1.8 -target 1.8 -d $classesDir -cp $classpath "@$argFile"
if ($LASTEXITCODE -ne 0) { Stop-Build "javac falhou (codigo $LASTEXITCODE)." }

# --- Recursos ---
foreach ($res in @("plugin.yml", "config.yml")) {
    $src = Join-Path $root $res
    if (-not (Test-Path $src)) { Stop-Build "Arquivo $res nao encontrado na raiz do projeto." }
    Copy-Item $src $classesDir
}

# --- Empacota ---
Write-Host "Gerando $JarName..." -ForegroundColor Cyan
& $jarExe cf $jarOut -C $classesDir .
if ($LASTEXITCODE -ne 0) { Stop-Build "jar falhou (codigo $LASTEXITCODE)." }

if ($NoCopy) {
    Write-Host ""
    Write-Host "OK: $jarOut (nao copiado, -NoCopy)" -ForegroundColor Green
    exit 0
}

# --- Copia para o servidor ---
$serverRunning = Get-CimInstance Win32_Process -Filter "Name='java.exe' OR Name='javaw.exe'" | Where-Object { $_.CommandLine -like "*spigot*" }
if ($serverRunning) {
    Stop-Build "O servidor parece estar ligado (PID $($serverRunning.ProcessId -join ', ')). Desligue-o antes de copiar o plugin. O jar compilado esta em: $jarOut"
}
$target = Join-Path $pluginsDir $JarName
try {
    Copy-Item $jarOut $target -Force
} catch {
    Stop-Build "Nao consegui copiar para $target. O servidor esta ligado? Desligue-o e rode de novo. ($($_.Exception.Message))"
}

$size = [math]::Round((Get-Item $target).Length / 1KB, 1)
Write-Host ""
Write-Host "OK: $target ($size KB)" -ForegroundColor Green
exit 0
