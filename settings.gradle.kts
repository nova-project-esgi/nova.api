rootProject.name = "nova"
include("app")
include("domain")
include("ports")
include("adapters:web")
findProject(":adapters:web")?.name = "web"
include("adapters:exposed")
findProject(":adapters:exposed")?.name = "exposed"
