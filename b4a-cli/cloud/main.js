const Disciplina = Parse.Object.extend("Disciplina");
const Aluno = Parse.Object.extend("Aluno");
const Turma = Parse.Object.extend("Turma");
const Periodo = Parse.Object.extend("Periodo");
const Modulo = Parse.Object.extend("Modulo");
const Avaliacao = Parse.Object.extend("Avaliacao");

Parse.Cloud.define("criar-disciplina", async (request) => {
	
	const descricao = request.params.descricao;
	if (descricao == null || descricao == "") throw "Disciplina não informada!"
	
	const disciplina = new Disciplina();
	disciplina.set("descricao", descricao);

	const novaDiscplina = await disciplina.save(null, {useMasterKey: true});
	return novaDiscplina;
});

Parse.Cloud.define("buscar-disciplinas", async (request) => {
	const query = new Parse.Query(Disciplina);
	query.ascending("descricao");

	const disciplina = await query.find({useMasterKey: true});
	return disciplina;
});

Parse.Cloud.define("criar-usuario-anonimo", async (request) => {
	const user = new Parse.User();
	user.set("username", request.params.username);
	user.set("password", request.params.password);

	const novoUsuario = await user.signUp(null, {useMasterKey: true});
	return novoUsuario.get("sessionToken");
});

Parse.Cloud.define("criar-usuario", async (request) => {
	const user = new Parse.User();
	user.id = request.params.id;
	user.set("username", request.params.email);
	user.set("password", request.params.password);
	user.set("email", request.params.email);

	const novoUsuario = await user.save(null, {useMasterKey: true});
	return novoUsuario;
});

Parse.Cloud.define("login", async (request) => {
	const usuario = await Parse.User.logIn(
		request.params.username, 
		request.params.password
	)

	// const objectId = usuario.get("objectId");
	const username = usuario.get("username");
	const email = usuario.get("email");
	const emailVerified = usuario.get("emailVerified");
	const sessionToken = usuario.get("sessionToken");

	const user = {
		username: username,
		email: email,
		emailVerified: emailVerified,
		sessionToken: sessionToken
	};
	return user;
});

Parse.Cloud.define("current-user", async (request) => {
	return request.user;
});

Parse.Cloud.define("criar-aluno", async (request) => {
	
	const userId = request.user.id;
	
	const user = new Parse.User();
	user.id = userId;

	const aluno = new Aluno();
	aluno.set("nome", "Estudante");
	aluno.set("userId", user);

	const novoAluno = await aluno.save(null, {useMasterKey: true});
	return novoAluno;
});

Parse.Cloud.define("alterar-aluno", async (request) => {
	if (request.params.alunoId == null) throw 'Informe o Código do Aluno!';

	const aluno = new Aluno();
	aluno.id = request.params.alunoId;

	const nome = request.params.nome;
	const matricula = request.params.matricula;

	if ((nome == null) && (matricula == null)) throw 'Informe o Nome ou Matrícula do Aluno!';

	aluno.set("nome", nome);
	aluno.set("matricula", matricula);

	const novoAluno = await aluno.save(null, {useMasterKey: true});
	return novoAluno;
});

Parse.Cloud.define("buscar-aluno", async (request) => {
	if (request.params.alunoId == null) throw 'Informe o Código do Aluno!';

	const query = new Parse.Query(Aluno);
	query.equalTo("objectId", request.params.alunoId);

	const aluno = await query.find({useMasterKey: true});
	return aluno;
});

Parse.Cloud.define("criar-turma", async (request) => {
	
	const nome = request.params.nome;
	const escola = request.params.escola;
	const turno = request.params.turno;
	const ano = request.params.ano;
	const dataInicio = request.params.dataInicio;
	const alunoId = request.params.alunoId;

	if (nome == null || nome == "") throw "Nome da Turma não informado!"
	if (escola == null || escola == "") throw "Nome da Escola não informado!"
	if (turno == null || turno == "") throw "Turno não informado!"
	if (ano == null || ano == "") throw "Ano não informado!"
	if (alunoId == null || alunoId == "") throw "Aluno Inválido!"
 
	const aluno = new Aluno();
	aluno.id = alunoId;

	const turma = new Turma();
	turma.set("nome", nome);
	turma.set("escola", escola);
	turma.set("turno", turno);
	turma.set("ano", ano);
	turma.set("dataInicio", dataInicio);
	turma.set("alunoId", aluno);

	const novaTurma = await turma.save(null, {useMasterKey: true});
	return novaTurma;
});

Parse.Cloud.define("alterar-turma", async (request) => {
	if (request.params.turmaId == null) throw 'Informe o Código da Turma!';

	const turma = new Turma();
	turma.id = request.params.turmaId;

	const nome = request.params.nome;
	const escola = request.params.escola;
	const turno = request.params.turno;
	const ano = request.params.ano;
	const dataInicio = request.params.dataInicio;

	turma.set("nome", nome);
	turma.set("escola", escola);
	turma.set("turno", turno);
	turma.set("ano", ano);
	turma.set("dataInicio", dataInicio);

	const novaTurma = await turma.save(null, {useMasterKey: true});
	return novaTurma;
});

Parse.Cloud.define("buscar-turma", async (request) => {
	if (request.params.turmaId == null) throw 'Informe o Código da Turma!';

	const query = new Parse.Query(Turma);
	query.equalTo("objectId", request.params.turmaId);

	const turma = await query.find({useMasterKey: true});
	return turma;
});

Parse.Cloud.define("criar-periodo", async (request) => {
	const descricao = request.params.descricao;
	const turmaId = request.params.turmaId;
	if (descricao == null || descricao == "") throw "Descrição do Periodo não informado!"
	
	const turma = new Turma();
	turma.id = turmaId;

	const periodo = new Periodo();
	periodo.set("descricao", descricao);
	periodo.set("turmaId", turma);

	const novaPeriodo = await periodo.save(null, {useMasterKey: true});
	return novaPeriodo;
});

Parse.Cloud.define("buscar-periodos", async (request) => {
	if (request.params.turmaId == null) throw 'Informe o Código da Turma!';

	const query = new Parse.Query(Periodo);
	query.equalTo("turmaId", request.params.turmaId);

	const periodos = await query.find({useMasterKey: true});
	return periodos;
});

Parse.Cloud.define("criar-modulo", async (request) => {
	const periodoId = request.params.periodoId;
	const disciplinaId = request.params.disciplinaId;
	
	const disciplina = new Disciplina();
	disciplina.id = disciplinaId;

	const periodo = new Periodo();
	periodo.id = periodoId;

	const modulo = new Modulo();
	modulo.set("periodoId", periodo);
	modulo.set("disciplinaId", disciplina);
	modulo.set("item", request.params.item);
	
	const novoModulo = await modulo.save(null, {useMasterKey: true});
	return novoModulo;
});

Parse.Cloud.define("deletar-modulo", async (request) => {
	if (request.params.moduloId == null) throw 'Informe o Código do Módulo';
	
	const modulo = new Modulo();
	modulo.id = request.params.moduloId;

	await modulo.destroy({useMasterKey: true});

	return 'Módulo '+request.params.moduloId+' deletado com sucesso!'
});

Parse.Cloud.define("buscar-modulos", async (request) => {
	if (request.params.periodoId == null) throw 'Informe o Código do Período!';

	const query = new Parse.Query(Modulo);
	query.equalTo("periodoId", request.params.periodoId);

	const modulos = await query.find({useMasterKey: true});
	return modulos;
});

Parse.Cloud.define("criar-avaliacao", async (request) => {
	const descricao = request.params.descricao;
	const nota = request.params.nota;
	const data = request.params.data;
	const recuperacao = request.params.recuperacao;
	const moduloId = request.params.moduloId;

	if (descricao == null || descricao == "") throw "Descrição da Avaliação não informada!"
	if (moduloId == null || moduloId == "") throw "Módulo Inválido!"
	
	const modulo = new Modulo();
	modulo.id = moduloId;
	
	const avaliacao = new Avaliacao();
	avaliacao.set("descricao", descricao);
	avaliacao.set("nota", nota);
	avaliacao.set("data", data);
	avaliacao.set("recuperacao", recuperacao);
	avaliacao.set("moduloId", modulo);

	const novaAvaliacao = await avaliacao.save(null, {useMasterKey: true});
	return novaAvaliacao;
});

Parse.Cloud.define("alterar-avaliacao", async (request) => {
	if (request.params.avaliacaoId == null) throw 'Informe o Código da Avaliação!';

	const avaliacao = new Avaliacao();
	avaliacao.id = request.params.avaliacaoId;

	const descricao = request.params.descricao;
	const nota = request.params.nota;
	const data = request.params.data;

	avaliacao.set("descricao", descricao);
	avaliacao.set("nota", nota);
	avaliacao.set("data", data);

	const novAvaliacao = await avaliacaoavaliacao.save(null, {useMasterKey: true});
	return novAvaliacao;
});

Parse.Cloud.define("deletar-avaliacao", async (request) => {
	if (request.params.avaliacaoId == null) throw 'Informe o Código da Avaliação';
	
	const avaliacao = new Avaliacao();
	avaliacao.id = request.params.avaliacaoId;

	await avaliacao.destroy({useMasterKey: true});

	return 'Avaliação '+request.params.avaliacaoId+' deletada com sucesso!'
});

Parse.Cloud.define("buscar-avaliacoes", async (request) => {
	if (request.params.moduloId == null) throw 'Informe o Código do Módulo!';

	const query = new Parse.Query(Avaliacao);
	query.equalTo("moduloId", request.params.moduloId);

	const avaliacoes = await query.find({useMasterKey: true});
	return avaliacoes;
});

Parse.Cloud.define("buscar-notas", async (request) => {
	const queryAvaliacao = new Parse.Query("Avaliacao");

	const pipeline = [
		{
			group: {
				objectId: null, // Podemos usar null para agregar todos juntos
				notaTotal: { $sum: "$nota" }
			}
		}
	];
	const somaNotas = await queryAvaliacao.aggregate(pipeline, { useMasterKey: true });
	return somaNotas;
});

Parse.Cloud.define("buscar-boletim-periodo", async (request) => {
    try {
        const query = new Parse.Query(Modulo);
        query.include("disciplinaId");
        query.equalTo("periodoId", request.params.periodoId);
    
        const resultados = await query.find({ useMasterKey: true });
    
        const boletim = [];
        
        for (const modulo of resultados) {
            const disciplina = modulo.get("disciplinaId");

			if (!disciplina) throw 'Disciplina não encontrada para o moduloId: '+modulo.id;
			
            const moduloId = modulo.id;
            const periodoId = modulo.get("periodoId");
			const item = modulo.get("item");
            const moduloDescricao = disciplina.get("descricao");
            const professor = modulo.get("professor");
    
            const queryAvaliacao = new Parse.Query(Avaliacao);
            queryAvaliacao.equalTo("moduloId", moduloId);
			const somaNotas = await queryAvaliacao.find({ useMasterKey: true });

			var notaTotal = 0.00;
			somaNotas.forEach((somaNotas) => {
				const nota = somaNotas.get("nota");
				notaTotal += nota ? nota : 0;	
			});

			// const pipeline = {
			// 		group: {
			// 			objectId: null, // Podemos usar null para agregar todos juntos
			// 			notaTotal: { $sum: "$nota" }
			// 		}
			// 	};
    
            boletim.push({
                moduloId,
                periodoId: periodoId.id,
				item,
                modulo: moduloDescricao,
                professor,
                notaTotal
            });
        }
        
        return boletim; 
    } catch (error) {
        throw new Error("Erro ao buscar boletim por período: " + (error.message || error));
    }
});
  
Parse.Cloud.define("buscar-boletim-modulo", async (request) => {

	const queryModulo = new Parse.Query(Modulo);
	queryModulo.include("disciplinaId");
	queryModulo.equalTo("objectId", request.params.moduloId);
	const modulo = await queryModulo.find({ useMasterKey: true });

	if (!modulo) throw 'Náo foi encontrado módulo com este id.';

	const disciplina = modulo[0].get("disciplinaId");
	const professor = modulo[0].get("professor");

	const query = new Parse.Query(Avaliacao);
	query.equalTo("moduloId", request.params.moduloId);

	const avaliacoes = await query.find({ useMasterKey: true });

	var notaTotal = 0.00;
	avaliacoes.forEach((somaNotas) => {
		const nota = somaNotas.get("nota");
		notaTotal += nota ? nota : 0;			
	});
	
	return {
		disciplina: disciplina.get("descricao"),
		professor,
		notaTotal
	};
});
